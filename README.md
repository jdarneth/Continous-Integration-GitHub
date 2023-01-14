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
   * Grund für Projekt (Umstieg in Firma & persönliches Interesse) 
**=> JAKOB** 

### Background / theoretical basis
(GIT flow, CI, ...)  
**=> JAKOB** 

### Project structure
**=> JAKOB** 

### Technology-Stack
![Technology-Stack](/docu/technStack.png)

 * **Java:** Code is implemented with programming language "Java"
 * **Git / Git flow:** Git is used as version control system & GitHub is used as (cloud-based) management tool for Git repositories. Git flow is a well-known strategy how a git workflow could work.
 * **Gradle:** Gradle is used as build tool for java code. Gradle is responsible for build, test and artifact creation. 
 * **GitHub-Actions:** GitHub-Actions are used to implement automatic task as **C**ontinous **I**ntegration (CI) pipeline. That includes automatically trigger build, create and provide artifacts and simplify release handling (e.g. create branch, adapt version, merge branch, tagging, ...). 

### Tutorial
In general, all (implementation) work is happening along the ``develop`` branch. If there are any changes, e.g. a new feature, a ``feature-branch`` must be created. If the git-flow framework is initialized (by command ``git flow init``), the feature-branch can be created automatically with command ``git flow feature start <name>``. In this branch, all necessary changes can be implemented, committed and pushed. If work is finished, the feature-branch can be merged back to develop (by command ``git flow feature finish``).

![Git flow: feature branch](/docu/gitFlow_feature.png)

 * For each push-event in the feature-branch, the build gets automatically started and verify language (build) and functional (tests) correctness.
 * For each push-event in the develop-branch (happens when finished feature is merged back), also the build is started. Furthermore, a JAR-File is generated to allow manual testing of the feature (if necessary?). 

---

The next step is, to release recently added and changed bugs or features. To start a release, it is enough to [create a new issue](https://github.com/jdarneth/Continous-Integration-GitHub/issues/new). By creating this issue, the release branch will be created and the release version will be automatically changed according to the issue-title. The issue must consider the following rules:
   * Issue title must match ``Release <versionNumber>``
   * Issue must have the label ``Release``
   
![Create Issue](/docu/tutorial_issue.png)

Now, keep the ticket open until everything is tested! If there are any bugs, add the bugfix to the release-branch. For each push-event in the release-branch, the build is started to verify the commit and furthermore, a JAR-File is generated to allow manual testing of the feature (if necessary?).
To finish the release, just close the issue. By closing the issue, the changes from the release branch are automatically merged to ``main`` and ``develop``. The main branch then contains all changes to be released and the develop contains the recently done bug fixes. Furthermore, the current state will be tagged and a "GitHub public release" is created for the repository. This release contains the current version of the source code and a ready-to-use artifact (JAR-File):

![GitHub public release](/docu/tutorial_release.png)

----

In general, for each push-event to the repository, visit GitHub-UI and check if the commit have passed all automated tests. All executed jobs can be found in the [GitHub-Actions-Tab](https://github.com/jdarneth/Continous-Integration-GitHub/actions) of your repository. 

### Lessons-learned / Problems
   * wie auf Folien nur ausführlicher
   * Probleme bechreiben
**=> JAKOB** 

