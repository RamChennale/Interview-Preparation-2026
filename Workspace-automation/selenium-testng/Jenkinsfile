pipeline {

    agent any

    tools {
        maven 'Maven_3.9'   // Name must match a Maven installation configured in
                            // Jenkins > Manage Jenkins > Global Tool Configuration
        jdk 'JDK_17'        // Name must match a JDK installation configured in Jenkins
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Browser to run the regression suite on')
        string(name: 'CUCUMBER_TAGS', defaultValue: '@regression', description: 'Cucumber tag expression to filter scenarios')
        booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Run browser in headless mode')
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '20'))
        disableConcurrentBuilds()
    }

    triggers {
        // Nightly regression run at 2 AM, in addition to manual/SCM triggers
        cron('H 2 * * *')
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code from SCM...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn -B clean compile test-compile'
            }
        }

        stage('Run Regression Tests') {
            steps {
                echo "Running Cucumber + TestNG regression suite on ${params.BROWSER}..."
                sh """
                    mvn -B clean test \
                        -Dbrowser=${params.BROWSER} \
                        -Dheadless=${params.HEADLESS} \
                        -Dcucumber.filter.tags="${params.CUCUMBER_TAGS}"
                """
            }
        }

        stage('Publish TestNG / Cucumber Reports') {
            steps {
                echo 'Publishing TestNG and Cucumber HTML reports...'
                publishHTML(target: [
                    reportName : 'Cucumber Report',
                    reportDir  : 'target/cucumber-reports',
                    reportFiles: 'cucumber.html',
                    keepAll    : true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: true
                ])
                publishHTML(target: [
                    reportName : 'TestNG Report',
                    reportDir  : 'target/surefire-reports',
                    reportFiles: 'index.html',
                    keepAll    : true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: true
                ])
            }
        }

        stage('Archive Results') {
            steps {
                junit allowEmptyResults: true, testResults: 'target/cucumber-reports/cucumber.xml, target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/cucumber-reports/**', allowEmptyArchive: true
                archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Regression suite completed successfully.'
        }
        failure {
            echo 'Regression suite failed. Check the Cucumber/TestNG reports for details.'
            // mail to: 'qa-team@example.com',
            //      subject: "FAILED: SauceDemo Regression Build #${env.BUILD_NUMBER}",
            //      body: "See ${env.BUILD_URL} for details."
        }
    }
}
