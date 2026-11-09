# Git Cheat Sheet — Daily Use & Interview Prep

## 1. Setup & Info
```bash
git init                        # initialize a new repo
git clone <url>                 # clone a remote repo
git status                      # check current state
git log --oneline --graph --all # visual commit history
git diff                        # unstaged changes
git diff --staged               # staged changes
```

## 2. Basic Daily Workflow
```bash
git add <file>          # stage a file (or . / -A for all)
git commit -m "msg"     # commit staged changes
git commit --amend      # edit last commit (msg or content)
git push                # push to remote
git pull                # fetch + merge from remote
git fetch                # download changes, don't merge
```

## 3. Branching
```bash
git branch                  # list branches
git branch <name>           # create branch
git checkout <branch>       # switch branch (older)
git switch <branch>         # switch branch (newer)
git checkout -b <branch>    # create + switch
git merge <branch>          # merge branch into current
git branch -d <branch>      # delete branch (safe)
git branch -D <branch>      # force delete branch
```

## 4. Undoing Things
```bash
git stash                   # save uncommitted work
git stash pop                # reapply + remove from stash
git stash list                # list all stashes
git stash apply stash@{2}    # apply specific stash
git stash drop                # delete a stash

git reset --soft HEAD~1      # undo commit, keep staged
git reset --mixed HEAD~1     # undo commit, keep unstaged (default)
git reset --hard HEAD~1      # undo commit, discard changes

git revert <commit>          # safe undo via new commit
git checkout -- <file>       # discard local changes (old)
git restore <file>           # discard local changes (new)
git rm --cached <file>       # untrack file, keep it locally
```

## 5. Remote Work
```bash
git remote -v                     # list remotes
git remote add origin <url>       # add remote
git push -u origin <branch>       # push + set upstream
git pull --rebase                 # pull with rebase instead of merge

git remote add upstream <url>     # for fork workflows
git fetch upstream
git rebase upstream/main
```

## 6. Interview-Favorite Commands

| Command | Purpose |
|---|---|
| `git rebase -i HEAD~3` | Interactive rebase — squash/reword/drop commits |
| `git cherry-pick <commit>` | Apply one specific commit onto current branch |
| `git bisect` | Binary search commits to find a bug-introducing commit |
| `git blame <file>` | See who changed each line and when |
| `git reflog` | Recover lost commits/branches (even unreachable ones) |
| `git tag v1.0.0` | Create a version tag |
| `git reset` vs `git revert` | reset rewrites history; revert adds a new undo commit (safe for shared branches) |

## Interview-favorite commands & concepts

These come up because they test whether you actually *understand* git internals, not just memorized commands.

**1. `git rebase` vs `git merge`**
Merge preserves history with a merge commit; 
rebase rewrites commit history to create a linear log. Be ready to explain interactive rebase:
```
git rebase -i HEAD~3
```
(squash, reword, drop commits)

**2. `git reset` vs `git revert`**
- `reset` rewrites history (dangerous on shared branches)
- `revert` creates a new commit undoing changes (safe for shared branches)
- Know `--soft`, `--mixed`, `--hard` differences

**3. `git cherry-pick <commit>`**
Apply a specific commit from one branch to another — common scenario: "how do you move just one fix to another branch?"

**4. `git stash` variants**
```
git stash save "message"
git stash list
git stash apply stash@{2}
git stash drop
```

**5. Merge conflicts**
Be ready to actually resolve one live: explain `<<<<<<<`, `=======`, `>>>>>>>` markers, then:
```
git add <resolved-file>
git commit
```

**6. `git blame` / `git bisect`**
- `blame` — who changed a line and when
- `bisect` — binary search through commits to find a bug-introducing commit (interviewers love this one, it's often unknown to junior devs)

**7. `.gitignore` and `git rm --cached`**
Removing a tracked file from git without deleting it locally — common "oops I committed node_modules" scenario.

**8. `git reflog`**
"How do you recover a deleted branch or lost commit?" — reflog is the answer, shows even unreachable commits.

**9. `git tag`**
```
git tag v1.0.0
git push origin v1.0.0
```

**10. Fork/PR workflow concepts**
Understand upstream vs origin:
```
git remote add upstream <url>
git fetch upstream
git rebase upstream/main
```


### Resolving a merge conflict
```bash
# after conflict markers appear:
# <<<<<<< HEAD
# your changes
# =======
# their changes
# >>>>>>> branch-name

# manually edit the file, then:
git add <resolved-file>
git commit
```

## 7. Key Interview Concepts to Explain Verbally
- `git fetch` vs `git pull` — fetch downloads only; pull = fetch + merge
- What the staging area (index) is and why it exists
- How git stores data internally: blobs, trees, commits, SHA-1 hashing
- `merge` vs `rebase` — history shape, when to use which
- `reset --soft/--mixed/--hard` — what each keeps or discards
- Detached HEAD state — what it is, how to get out of it
- Squashing commits before opening a PR
- `git branch -d` vs `-D` — safe vs forced delete
- Fork/PR workflow: origin vs upstream

## 8. Quick Scenario Answers
- **"Oops, committed node_modules"** → `git rm --cached -r node_modules` + add to `.gitignore`
- **"Need to move one fix to another branch"** → `git cherry-pick <commit>`
- **"Accidentally deleted a branch"** → `git reflog` → find commit → `git checkout -b <branch> <sha>`
- **"Need to find which commit broke something"** → `git bisect start` → `git bisect bad` / `git bisect good <sha>`
- **"Need to clean up messy commit history before PR"** → `git rebase -i HEAD~N`

