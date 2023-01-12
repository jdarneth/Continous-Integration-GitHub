# Continous-Integration Project
```
by Arneth Jakob (k11916206) & Peirlberger Jürgen (k11908871)
```
---
 * Proposal see [here](https://github.com/jdarneth/Continous-Integration-GitHub/blob/main/PROPOSAL.md)
 * Implementation see [here](https://github.com/jdarneth/Continous-Integration-GitHub/tree/main/Blackjack)
 * Miro board see [here](https://miro.com/app/board/uXjVP2JsBig=/?share_link_id=493134313895)

---
## Documentation 

### Motivation
The reason why we chose this topic is that we are working both in the same company in Linz. Currently, the company uses SVN as version control system but the company plans to migrate the whole software stack from SVN to Git. The process already started and will continue the next few months. Due to the migration from SVN to Git, we as developers get in touch with Git almost on a daily basis. Therefore, it is interesting for us to implement a Continous Integration and an own Git flow since we are getting a lot in touch with Git in these days. Also a personal interest for the topic is existing.

### Background / theoretical basis
With this project a Continuous Integration workflow for our chosen Java project Black Jack was implemented. For this process, GitHub (Git) was used as version control system and GitHub Actions (with Gradle) was used as a build tool. The result of the build (the artifact) is a JAR file, which is persisted in the public release of each version. Furthermore, Git Flow was used for this project.

**Git Flow**

![Git-Flow](/docu/gitFlow.png)

The core branch for developing is the develop branch. Changes from this branch are not released directly. If a feature must be implemented, a new feature branch will branch off the develop branch. After the implementation of the feature, the changes are merged back into the develop branch. If a release has to be created, a new issue with a label _release_ must be opened. For this process, a new release branch is created out of the develop branch. After all implementation steps are done for the release, the issue will be closed. At last the release branch is merged back automatically into the main and the develop branch. Hotfixes are only for time-critical bugs, which should be released again as soon as possible.

**GitHub Actions**

With GitHub Actions a workflow for the Continuous Integration will be defined. It will be used to automatically test and release the code.

  * Feature branch: Trigger following workflow on GIT push. Execute build and tests (can fail temporarily).
  * Develop branch: Trigger following workflow on GIT push. Execute build and tests (must be successful). Create JAR snapshot for testing. If the event CreateIssue occurs, a new release branch is created from develop branch (release version is set in the issue title). In the build.gradle file the version is automatically adapted.
  * Release branch: Trigger following workflow on GIT push. Execute build and tests (must be successful). If the event CloseIssue occurs, the release gets finished. This means that it is merged back to the main branch and the develop branch. A GitHub public release is created. The artifact from the build is persisted (annotated by the version) in the repository. Create a new Tag with new version on main branch.
  * Main branch: No special workflow (only stable versions are merged).
  * Hotfix branch: Trigger following workflow on GIT push. Execute build and tests.

### Project structure
**=> JAKOB** 

### Technology-Stack
![Technology-Stack](/docu/technStack.png)

 * **Java:** Code is implemented with programming language "Java"
 * **Git / Git flow:** Git is used as version control system & GitHub is used as (cloud-based) management tool for Git repositories. Git flow is a special strategy for a git workflow.
 * **Gradle:** Gradle is used as build tool for java code. Gradle is responsible for build, test and artifact creation. 
 * **GitHub-Actions:** GitHub-Actions are used to implement automatic task as **C**ontinous **I**ntegration (CI) pipeline. That includes automatically trigger build, create and provide artifacts and simplify release handling (e.g. create branch, adapt version, merge branch, tagging, ...). 

### Tutorial
(step-by-step instructions & reproducibility) --> MIRO stuff
**=> JÜRGEN** 

### Lessons learned / Problems

Lessons learned:
  * We learned how to setup a Continous Integration with GitHub Actions.
  * We learned how to define an own Git Flow.
  * We learned how to plan and implement release strategies.
  * We learned how to use differnet branches correctly in the Git Flow.

Problems:
  * After the release is closed, a public release will be created and the release branch will be merged back automatically into the main and the develop branch. For auto-merging we used over quite some time a wrong or inappropriate GitHub Action. The problem was that the auto-merging process sometimes worked and somestimes failed. It was also possible that for the main branch it worked and for the develop branch it failed (or the other way around), although both branches were identical. For figuring out where the problem comes from, we tried several things. At the beginning, we definded separate workflows for the main branch and the develop branch to auto-merge back the release branch. But this did not solve the problem. After quite some time we decided to use another library for auto-merging. The switch to the other library did the job and auto-merging worked for both branches.
  * Furthermore, after closing the release, an artifact of the release is created and released together with the public release. The problem was that we could not find out to which path the workflow uploaded the artifact and what file type the artifact had. After printing out some bash commands in the workflow, we figured out the path and file type to upload the artifact to the public release.

