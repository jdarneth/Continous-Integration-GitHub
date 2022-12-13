# PROPOSAL - Continuous Integration | GitHub Actions and Git Flow

## Project Team
- Jakob Arneth, k11916206
- Jürgen Peirlberger, k11908871

## Project Description
Project: GitHub Action and Git Flow

The goal of this project is to implement a Continuous Integration workflow for our chosen Java project [Black Jack](https://github.com/jdarneth/Continous-Integration-GitHub/tree/main/Blackjack). For this process GitHub (Git) is used as version control system and GitHub Actions (with Gradle) is used as a build tool. The result of the build (the artifact) is a JAR file, which is persisted for each version in a defined folder in the repository and a GitHub public release is created. Furthermore, Git Flow is used for this project.

### Git Flow

**Branches**:

- Feature Branch: The feature branches off the develop branch for creating new features. For each GIT push the tests and the build are executed and it does not matter whether they are successful or not. That means that the build and the tests are allowed to fail temporarily (for temporary changes). If the feature is fully developed, the changes are merged into the develop branch.
- Developer Branch: Every time changes are committed and pushed all tests are executed automatically. If errors occur, they should be shown immediately after test execution and the GIT push is cancelled. Each build of the develop branch must be successful (only finished features are merged).
- Release Branch: The release branch is created out of the develop branch when a new release will start (draft). In this branch bugfixes can be directly changed and will be merged back to develop branch. If the release is successfully finished, it is merged back to main branch.
- Main Branch: Just containing stable released versions. No development is happening on this branch.
- Hotfix Branch: The hotfix branch is used to fix critical bugs from a released version and the results are merged into the main branch (new tag is created) and the develop branch.

**Workflow**:

The core branch to develop is the develop branch. This is the branch where the actual work is happening. The changes in the branch are not released directly. All code changes branch off the develop branch (called feature branch) and merged back to develop branch after code review. If a release starts (draft stage), a new release branch with next version is created out of the development branch by creating an issue. To finalize a release, the issue will be closed. The release branch is merged back to the main branch. Hotfixes are only for time-critical bugs, which should be released again as soon as possible. 

### GitHub Actions
With GitHub Actions a workflow for the Continuous Integration will be defined. It will be used to automatically test and release the code.

**Feature branch**: 

* Trigger following workflow on GIT push. Execute build and tests (can fail temporarily).

**Develop branch**: 

* Trigger following workflow on GIT push. Execute build and tests (must be successful). Create JAR snapshot for testing. 
* If the event *CreateIssue* occurs, a new release branch is created from develop branch (release version is set in the issue title). In the *build.gradle* file the version is automatically adapted.

**Release branch**: 

* Trigger following workflow on GIT push. Execute build and tests (must be successful). 
* If the event *CloseIssue* occurs, the release gets finished. This means that it is merged back to the main branch and the develop branch. A GitHub public release is created. The artifact from the build is persisted (annotated by the version) in the repository. Create a new Tag with new version on main branch.

**Main branch**: 

* No special workflow (only stable versions are merged).

**Hotfix branch**:

* Trigger following workflow on GIT push. Execute build and tests.

## Milestones
- 24.12. Git Flow defined and installed
- 06.01. GitHub Actions implemented
- 13.01. GitHub Actions tested
- 15.01. Documentation finished
- 18.01. Presentation

## Responsibilities
- Git Flow Definition and Installation: both
- GitHub Actions for push: Jakob
- GitHub Actions for issues: Jürgen 
- GitHub Actions Tests: both
- Documentation: both

## Presentation
- Add new feature.
- Merge feature.
- Create release by creating issue.
- Add bug fixes.
- Merge release by closing issue.
