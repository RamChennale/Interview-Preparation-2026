# Linux Commands — Files, Directories & System Fundamentals
## Quick Reference for QA Automation Engineers (CI/CD agents, Jenkins servers, Docker containers)

As automation QA, you'll SSH into Jenkins agents, debug failed pipeline runs, check logs, manage test artifacts, and 
sometimes containerized test environments — these commands cover what you'll actually use.

linux home path:
\\wsl.localhost\Ubuntu\home\ram_chennale
Find the Files on Windows (WSL)Because you are using the Windows Subsystem for Linux (WSL), 
you can open this exact folder directly inside Windows File Explorer.
Open Windows File Explorer 
	(Win + E).Type 
	\\wsl$ into the top address bar.Press Enter.Open your Linux distribution folder (e.g., Ubuntu).Navigate to: 
	home > ram_chennale


## 1. Navigation

|Command| Purpose | Example |
|---	|---							  |---|
| `pwd` | Print current working directory | `pwd` |
| `ls` 	| List files/directories 		  | `ls -l` (long format), `ls -a` (show hidden), `ls -lh` (human-readable sizes), `ls -lt` (sort by time) |
| `cd` 	| Change directory 				  | `cd /var/log`, `cd ..` (up one), `cd ~` (home), `cd -` (previous dir) |
| `tree`| Show directory structure as a tree | `tree -L 2` (2 levels deep) |

---

## 2. File & Directory Operations

| Command 	 | Purpose | Example |
|---		 |---|---|
| `mkdir`	 | Create directory | `mkdir test-results`, `mkdir -p a/b/c` (create nested path) |
| `rmdir`	 | Remove empty directory | `rmdir old-logs` |
| `rm`		 | Remove file(s) | `rm file.txt`, `rm -r folder/` (recursive), `rm -rf folder/` (force, no prompt — **use carefully**) |
| `touch`	 | Create empty file / update timestamp | `touch config.properties` |
| `cp` 		 | Copy file/directory | `cp source.txt dest.txt`, `cp -r sourceDir/ destDir/` |
| `mv` 		 | Move or rename | `mv old.txt new.txt`, `mv report.html /mnt/user-data/outputs/` |
| `find` 	 | Search for files/directories | `find . -name "*.log"`, `find . -mtime -1` (modified in last 1 day) |
| `locate` 	 | Fast file search (uses indexed DB) | `locate pom.xml` |
| `file` 	 | Determine file type | `file screenshot.png` |
| `stat` 	 | Detailed file metadata | `stat testng.xml` |
| `ln` 		 | Create links | `ln -s /original/path linkname` (symbolic link) |

---

## 3. Viewing & Reading File Contents

| Command | Purpose | Example |
|---|---|---|
| `cat` | Print entire file content | `cat testng-results.xml` |
| `less` / `more` | View file page-by-page (scrollable, `q` to quit) | `less pipeline.log` |
| `head` | View first N lines | `head -n 20 error.log` |
| `tail` | View last N lines | `tail -n 50 build.log` |
| `tail -f` | **Live-follow** a growing log file — critical for watching Jenkins/test logs in real time | `tail -f /var/log/jenkins/jenkins.log` |
| `wc` | Word/line/char count | `wc -l failed_tests.txt` (count lines = count failed tests) |
| `diff` | Compare two files | `diff expected.json actual.json` |
| `nano` / `vi` / `vim` | Edit files directly in terminal | `vi config.properties` |

---

## 4. Searching Inside Files (grep — extremely important for QA)

| Command | Purpose | Example |
|---|---|---|
| `grep` 	| Search for text pattern in file(s) | `grep "FAILED" build.log` |
| `grep -i` | Case-insensitive search | `grep -i "error"` |
| `grep -r` | Recursive search in all files under a directory | `grep -r "NullPointerException" ./logs/` |
| `grep -n` | Show line numbers of matches | `grep -n "Exception" app.log` |
| `grep -v` | Invert match (show lines NOT matching) | `grep -v "PASS" results.log` |
| `grep -c` | Count matching lines | `grep -c "FAIL" results.log` |
| `grep -A / -B / -C` | Show N lines After/Before/around a match (for stack traces) | `grep -A 5 "Exception" app.log` |

**Real use case:** `grep -r "TIMEOUT" /var/jenkins_home/workspace/MyPipeline/logs/` 
	— quickly finding which test logs had timeout issues across an entire workspace.

---

## 5. File Permissions & Ownership

| Command | Purpose | Example |
|---|---|---|
| `chmod` | Change file permissions | `chmod 755 script.sh`, `chmod +x run_tests.sh` (make executable) |
| `chown` | Change file owner | `chown jenkins:jenkins deploy.sh` |
| `ls -l` | View permissions | `-rwxr-xr-x` (owner/group/others: read/write/execute) |

**Permission cheat:** `r=4, w=2, x=1` → `755` = owner(rwx=7), group(r-x=5), others(r-x=5). 
	This comes up constantly when Jenkins can't execute a shell script (`Permission denied` errors) — 
	the fix is almost always `chmod +x script.sh`.

---

## 6. Compression & Archiving

| Command | Purpose | Example |
|---|---|---|
| `tar -czvf` | Create compressed archive | `tar -czvf logs.tar.gz logs/` |
| `tar -xzvf` | Extract compressed archive | `tar -xzvf logs.tar.gz` |
| `zip` / `unzip` | Zip/unzip files | `zip -r report.zip report/`, `unzip report.zip` |

**Automation use case:** Archiving test execution artifacts (screenshots, Allure reports) before uploading them or attaching to a Jenkins build.

---

## 7. Process & System Monitoring

| Command | Purpose | Example |
|---|---|---|
| `ps` | List running processes | `ps -ef` (full format), `ps aux \| grep java` (find running Java/Selenium processes) |
| `top` / `htop` | Live view of CPU/memory usage per process | `top` |
| `kill` | Terminate a process by PID | `kill 1234`, `kill -9 1234` (force kill) |
| `pkill` | Kill process by name | `pkill -f chromedriver` (kill hung ChromeDriver instances — very common!) |
| `jobs` | List background jobs in current shell | `jobs` |
| `nohup` | Run a process that survives terminal disconnect | `nohup mvn test &` |
| `df -h` | Disk space usage (human-readable) | `df -h` — check if Jenkins agent is out of disk space |
| `du -sh` | Size of a directory | `du -sh test-output/` |
| `free -h` | Memory usage | `free -h` |
| `uptime` | System uptime & load average | `uptime` |

**Real scenario:** After a pipeline crash, running `ps aux | grep chrome` then `pkill -f chromedriver` to clean up zombie browser processes 
left behind by a crashed Selenium session — a very common CI agent issue.

---

## 8. Networking

| Command | Purpose | Example |
|---|---|---|
| `ping` | Test connectivity to a host | `ping api.myapp.com` |
| `curl` | Make HTTP requests from terminal | `curl -X GET https://api.myapp.com/health` |
| `wget` | Download a file from URL | `wget https://repo.com/file.jar` |
| `netstat` / `ss` | View open ports/connections | `netstat -tulnp \| grep 8080`, `ss -tulnp` |
| `telnet` | Test if a port is reachable | `telnet appserver.com 8080` |
| `nslookup` / `dig` | DNS lookup | `nslookup myapp.com` |
| `hostname -I` | Get machine's IP address | `hostname -I` |

**Automation use case:** `curl -I https://myapp-qa.com` to quickly check if the test environment is up before triggering a full regression run — saves wasted pipeline minutes.

---

## 9. Environment & Variables

| Command | Purpose | Example |
|---|---|---|
| `env` | List all environment variables | `env` |
| `echo $VAR_NAME` | Print value of a variable | `echo $JAVA_HOME`, `echo $PATH` |
| `export` | Set an environment variable | `export BROWSER=chrome` |
| `which` | Show path of an executable | `which java`, `which mvn` |
| `java -version` / `mvn -version` | Check installed tool versions | Common first debugging step on a new Jenkins agent |

---

## 10. User & System Info

| Command | Purpose | Example |
|---|---|---|
| `whoami` | Current logged-in user | `whoami` |
| `id` | User ID and group info | `id` |
| `uname -a` | Kernel/system info | `uname -a` |
| `cat /etc/os-release` | OS/distro version | `cat /etc/os-release` |
| `history` | Show command history | `history \| grep mvn` |
| `date` | Show current date/time | `date` |

---

## 11. File Transfer (between local machine & remote server)

| Command | Purpose | Example |
|---|---|---|
| `scp` | Secure copy file to/from remote server | `scp report.html user@server:/home/user/reports/` |
| `rsync` | Efficient sync of files/directories | `rsync -avz test-results/ user@server:/backup/` |
| `ssh` | Connect to remote server | `ssh user@jenkins-agent-01` |

---

## 12. Command Combinations (Pipes & Redirection) — Very Practical

| Symbol | Purpose | Example |
|---|---|---|
| `\|` | Pipe output of one command into another | `ps aux \| grep java` |
| `>` | Redirect output to file (overwrite) | `mvn test > output.log` |
| `>>` | Redirect output to file (append) | `echo "Run complete" >> summary.log` |
| `<` | Take input from a file | `mysql < script.sql` |
| `&&` | Run second command only if first succeeds | `mvn clean && mvn test` |
| `;` | Run commands sequentially regardless of success | `cd logs; ls` |
| `&` | Run command in background | `mvn test &` |

**Real examples you'll actually type:**
```bash
grep -c "FAILED" results.log                          # count failed tests
cat results.log | grep "FAILED" | wc -l                # same thing, piped
find . -name "*.png" -mtime -1                          # today's failure screenshots
tail -f jenkins.log | grep --line-buffered "ERROR"      # live filter for errors
mvn clean test -Psmoke > smoke_run.log 2>&1 &            # run in background, log everything
```

---

## Quick Cheat Sheet — Most-Used Commands Daily as QA on Linux CI Agents

```bash
pwd                                  # where am I
ls -la                               # what's here
cd /path/to/workspace                # navigate
tail -f build.log                    # watch live pipeline output
grep -i "error" build.log            # find errors fast
ps aux | grep chrome                 # check for hung browser processes
pkill -f chromedriver                # kill hung browser processes
df -h                                # check disk space (common CI failure cause)
chmod +x run.sh                      # fix "permission denied" script errors
tar -czvf artifacts.tar.gz reports/  # package test artifacts
scp report.html user@host:/path/     # send report to another server
curl -I https://myapp-qa.com         # check environment is up before test run
history | grep mvn                   # find that maven command you ran earlier
```

---

*Interview tip: A very common senior QA interview question is — "The Jenkins pipeline is stuck/failing on a Linux agent — how do you debug it?" 
*	A strong answer chains several of these together: 
*	check `df -h` (disk space) → `tail -f` the build log → `grep -i error` on it → `ps aux | grep <process>` 
*	to check for zombie processes → `chmod`/permission checks if it's a script execution failure. 
*	Having this diagnostic sequence ready shows real hands-on CI/CD ownership, not just command memorization.*
