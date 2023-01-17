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
The reason why we chose this topic is that we are working both in the same company in Linz. Currently, the company uses SVN as version control system but the company plans to migrate the whole software stack from SVN to Git. The process has already started and will continue the next few months. Due to the migration from SVN to Git, we as developers get in touch with Git almost on a daily basis. Therefore, it is interesting for us to implement a Continous Integration and an own Git flow since we are getting a lot in touch with Git in these days. Also a personal interest for the topic is existing.

### Background / theoretical basis
With this project a Continuous Integration workflow for our chosen Java project Black Jack was implemented. For this process, GitHub (Git) was used as version control system and GitHub Actions (with Gradle) was used as a build tool. The result of the build (the artifact) is a JAR file, which is persisted in the public release of each version. Furthermore, a Git flow was introduced for this project.

**Git Flow**

![Git-Flow](/docu/gitFlow.png)

The core branch for developing is the develop branch. Changes from this branch are not released directly. 

If a feature must be implemented, a new feature branch will branch off the develop branch. After the implementation of the feature, the changes are merged back into the develop branch. 

If a release should be created, a new issue with a label _release_ must be opened. For this process, a new release branch will be created automatically out of the develop branch. After all implementation steps are done for the release, the issue will be closed. At last, the release branch is merged back automatically into the main and the develop branch. 

Hotfixes are only for time-critical bugs, which should be released again as soon as possible.

**GitHub Actions**

With GitHub Actions a workflow for the Continuous Integration will be defined. It will be used to automatically test and release the code.

  * Feature branch: Trigger following workflow on GIT push. Execute build and tests (can fail temporarily).
  * Develop branch: Trigger following workflow on GIT push. Execute build and tests (must be successful). Create JAR snapshot for testing. If the event CreateIssue  with the label _release_ occurs, a new release branch is created from develop branch (release version is set in the issue title). In the build.gradle file the version is automatically adapted.
  * Release branch: Trigger following workflow on GIT push. Execute build and tests (must be successful). If the event CloseIssue occurs, the release gets finished. This means that it is merged back to the main branch and the develop branch. A GitHub public release is created. The artifact from the build is persisted (annotated by the version) in the repository. Create a new Tag with new version on main branch.
  * Main branch: No special workflow (only stable versions are merged).
  * Hotfix branch: Trigger following workflow on GIT push. Execute build and tests.

### Project structure
  * The GitHub Actions workflows for the project are located in the _.github/workflows_ directory.
  * The source code and the tests are located in the _src_ directory.
  * The project is a standard gradle project with all related files for settings.
  * In the _gradle.properties_ file the current version of the project is defined.

### Technology-Stack
![Technology-Stack](/docu/technStack.png)

 * **Java:** Code is implemented with programming language Java
 * **Git / Git flow:** Git is used as version control system & GitHub is used as (cloud-based) management tool for Git repositories. Git flow is a well-known strategy how a git workflow could work.
 * **Gradle:** Gradle is used as build tool for Java code. Gradle is responsible for build, test and artifact creation. 
 * **GitHub-Actions:** GitHub-Actions are used to implement automatic tasks as **C**ontinous **I**ntegration (CI) pipeline. That includes to automatically trigger the build, create and provide artifacts and simplify release handling (e.g. create branch, adapt version, merge branch, tagging, ...). 

### Tutorial
In general, all (implementation) work is happening along the ``develop`` branch. If there are any changes, e.g. a new feature, a ``feature-branch`` must be created. If the git-flow framework is initialized (by command ``git flow init``), the feature-branch can be created automatically with command ``git flow feature start <name>``. In this branch, all necessary changes can be implemented, committed and pushed. If work is finished, the feature-branch can be merged back to develop (by command ``git flow feature finish``).

![Git flow: feature branch](/docu/gitFlow_feature.png)

 * For each push-event in the feature-branch, the build gets automatically started to verify the language (build) and the functional (tests) correctness.
 * For each push-event in the develop-branch (happens when finished feature is merged back), also the build is started. Furthermore, a JAR-File is generated to allow manual testing of the feature (if necessary). 

---

The next step is, to release recently added and changed bugs or features. To start a release, it is enough to [create a new issue](https://github.com/jdarneth/Continous-Integration-GitHub/issues/new). By creating this issue, the release branch will be created and the release version will be automatically changed according to the issue-title. The issue must consider the following rules:
   * Issue title must match ``Release <versionNumber>``
   * Issue must have the label ``Release``
   
![Create Issue](/docu/tutorial_issue.png)

Now, keep the ticket open until everything is tested! If there are any bugs, add the bugfix to the release-branch. For each push-event in the release-branch, the build is started to verify the commit and furthermore, a JAR-File is generated to allow manual testing of the feature (if necessary).
To finish the release, just close the issue. By closing the issue, the changes from the release branch are automatically merged to ``main`` and ``develop``. The main branch then contains all changes to be released and the develop contains the recently done bug fixes. Furthermore, the current state will be tagged and a "GitHub public release" is created for the repository. This release contains the current version of the source code and a ready-to-use artifact (JAR-File):

![GitHub public release](/docu/tutorial_release.png)

----

In general, for each push-event to the repository, visit GitHub-UI and check if the commit has passed all automated tests. All executed jobs can be found in the [GitHub-Actions-Tab](https://github.com/jdarneth/Continous-Integration-GitHub/actions) of your repository. 

### Lessons learned / Problems

Lessons learned:
  * We learned how to setup a Continous Integration with GitHub Actions.
  * We learned how to define an own Git Flow.
  * We learned how to plan and implement release strategies.
  * We learned how to use different branches correctly in the Git Flow.

Problems:
  * After the release is closed, a public release will be created and the release branch will be merged back automatically into the main and the develop branch. For auto-merging we used over quite some time a wrong or inappropriate GitHub Action. The problem was that the auto-merging process sometimes worked and somestimes failed. It was also possible that for the main branch it worked and for the develop branch it failed (or the other way around), although both branches were identical. For figuring out where the problem comes from, we tried several things. At the beginning, we definded separate workflows for the main branch and the develop branch to auto-merge back the release branch. But this did not solve the problem. After quite some time we decided to use another library for auto-merging. The switch to the other library did the job and auto-merging worked for both branches.
  * Furthermore, after closing the release, an artifact of the release is created and released together with the public release. The problem was that we could not find out to which path the workflow uploaded the artifact and what file type the artifact had. After printing out some bash commands in the workflow, we figured out the path and the file type to upload the artifact to the public release.

