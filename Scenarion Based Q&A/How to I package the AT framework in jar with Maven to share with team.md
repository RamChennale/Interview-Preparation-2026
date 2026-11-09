## What to say in the interview

"I package the framework with the ***maven-shade-plugin*** so all dependencies go into one jar, with TestNG as the main class. 
The teammate only needs Java and runs `java -jar framework.jar testng.xml`. 
Browser and tags can be overridden with `-D` flags. 

In practice I'd also let Jenkins build and archive the jar, or just share the repo, because that keeps everyone on the latest code."



#How to I package the AT framework in jar with Maven to share with team
In your repo all the framework code is under `src/test`, 
and a normal Maven jar and the shade plugin only pick up `src/main` and compile-scope dependencies. 
A plain `mvn package` would give a jar with no tests. 
So I'd add a separate profile that packages the test sources, which leaves your normal `mvn test` build untouched.

## 1. Changes to `pom.xml` (selenium-testng example)

Make the source directory a property:

```xml
<properties>
    <framework.src>src/main/java</framework.src>
</properties>

<build>
    <finalName>saucedemo-cucumber-testng</finalName>
    <sourceDirectory>${framework.src}</sourceDirectory>
    <!-- existing compiler + surefire plugins stay as they are -->
</build>
```

Add the packaging profile:

```xml
<profiles>
  <profile>
    <id>package-jar</id>
    <properties>
      <framework.src>src/test/java</framework.src>
    </properties>
    <build>
      <resources>
        <resource><directory>src/test/resources</directory></resource>
      </resources>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-shade-plugin</artifactId>
          <version>3.6.0</version>
          <executions>
            <execution>
              <phase>package</phase>
              <goals><goal>shade</goal></goals>
              <configuration>
                <createDependencyReducedPom>false</createDependencyReducedPom>
                <transformers>
                  <!-- keeps Selenium/Cucumber/TestNG service files -->
                  <transformer implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer"/>
                  <!-- entry point = TestNG -->
                  <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                    <mainClass>org.testng.TestNG</mainClass>
                  </transformer>
                </transformers>
                <filters>
                  <filter>
                    <artifact>*:*</artifact>
                    <excludes>
                      <exclude>META-INF/*.SF</exclude>
                      <exclude>META-INF/*.DSA</exclude>
                      <exclude>META-INF/*.RSA</exclude>
                    </excludes>
                  </filter>
                </filters>
              </configuration>
            </execution>
          </executions>
        </plugin>
      </plugins>
    </build>
  </profile>
</profiles>
```

- `ServicesResourceTransformer` merges the `META-INF/services` files. Without it, Selenium and Cucumber can fail at runtime in a fat jar.
- The `*.SF/*.DSA/*.RSA` excludes prevent the "Invalid signature file digest" error.
- `mainClass` is TestNG, so the jar takes a `testng.xml` as its argument.

## 2. Two code fixes so it works outside the project folder

1. **Feature path.** `TestRunner` uses `features = "src/test/resources/features"`, which is a file-system path. Change it to `features = "classpath:features"`.
2. **Config path.** `TestNGBaseTest` reads `user.dir + "/src/test/resources/config.properties"`, which won't exist on a teammate's machine. Load it from the classpath instead, as `ConfigReader` already does with `getResourceAsStream("config.properties")`.

Also replace the hard-coded Windows `reportConfigPath` if you use it. Your `pom.xml` also has `<n>` where `<name>` is expected, so check that Maven doesn't complain.

## 3. Build and run

```
mvn clean package -Ppackage-jar -DskipTests
```

Output: `target/saucedemo-cucumber-testng.jar`

Run it:

```
java -jar target/saucedemo-cucumber-testng.jar testng.xml
java -Dbrowser=firefox -Dcucumber.filter.tags="@smoke" -jar target/saucedemo-cucumber-testng.jar testng.xml
```

`-D` overrides still work because `ConfigReader` checks system properties first.

## 4. What to hand over

- The jar
- `testng.xml` (kept outside the jar so they can edit it)
- A short README: Java 17 required, internet needed for WebDriverManager, and the run commands above

The reports appear in the folder they run from, under `reports/` and `target/`.

