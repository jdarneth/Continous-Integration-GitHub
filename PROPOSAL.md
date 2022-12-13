# PROPOSAL - Continuous Integration | GitHub Actions and Git Flow

## Project Team
- Jakob Arneth, k11916206
- Jürgen Peirlberger, k11908871

## Project Description
Project: GitHub Action and Git Flow

The goal of this project is to implement a Continuous Integration workflow for our chosen Java project [Black Jack](https://github.com/jdarneth/Continous-Integration-GitHub/tree/main/Blackjack). For this process GitHub (Git) is used as version control system and GitHub Actions (with Gradle) is used as a build tool. The result of the build (the artifact) is a JAR file, which persisted for each version in a defined folder in the repository. Furthermore, a Git Flow is defined for this project.

### GitHub Actions
With GitHub Actions the a workflow for the Continuous Integration will be defined. It will be used to automatically test and release the code.

### Git Flow
For the project an own Git Flow is defined. 

*Branches*:
- Feature Branch: Tests are executed but are not restricted for a GIT push.
- Developer Branch: Every time changes are committed and pushed all tests are executed automatically. If errors occur, they should be shown immediately after test execution and the GIT push is cancelled.
- Release Branch: The release branch is created out of the develop branch when a new release will start (draft). In this branch bugfixes can be directly changed and will be merged back to develop branch.
- Main Branch: Just containing stable released versions. No development is happening on this branch.
- Hotfix Branch: The hotfix branch is used to fix critical bugs from a released version and the results are merged into the main branch (new tag is created) and the develop branch.

*Workflow*:

The core branch to develop is the develop branch. This is the branch where the actual work is happening. The changes in the branch are not released directly. All code changes branch off the develop branch (called feature branch) and merged back to develop branch after code review. If a release starts (draft stage), a new release branch with next version is created out of the development branch. To finalize a release, the release branch is merged to the main branch.

### 

## Milestones
- 31.12. Git Flow defined and installed
- 06.01. GitHub Actions implemented
- 13.01. GitHub Actions tested
- 15.01. Documentation finished
- 18.01. Presentation

## Responsibilities
- Git Flow Definition and Installation: Jürgen
- GitHub Actions Implementation: Jakob
- GitHub Actions Tests: both
- Documentation: both
