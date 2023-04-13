# airbyte-protocol

Declares the Airbyte Protocol.

## Key Files
* `airbyte_protocol.yaml` - declares the Airbyte Protocol (in JSONSchema)
* `io.airbyte.protocol.models` - this package contains various java helpers for working with the protocol.


## Pull Requests Titles Must Conform to Conventional Commits Convention
We are leveraging the [Release Please Action](https://github.com/marketplace/actions/release-please-action) to manage our version bumping and releasing.
This action relies on the use of the [conventional commits convention](https://www.conventionalcommits.org/en/v1.0.0/) to determine whether to bump major, minor, or patch versions. Since we use squash merging, the only commits we see in our history are the titles of our pull requests. This is why we are following conventional commits for our pull request titling. Your actual commits do not need to follow this convention because they never show up in the git history.

Here is a summary of what Release Please looks for in your pull request title.

The most important prefixes you should have in mind are:
* fix: which represents bug fixes, and correlates to a SemVer patch.
* feat: which represents a new feature, and correlates to a SemVer minor.
* feat!:, or fix!:, refactor!:, etc., which represent a breaking change (indicated by the !) and will result in a SemVer major.

Release Please will create a pull request when it believes there is a potential new version to be released. It compiles a change log based
on the commit history sinced last release. It updates this PR appropriately everytime a PR is merged. If you desire to release a new version, you
would approve and merge this PR.

The PR looks something like this. ![example-release-please-pr](https://github.com/google-github-actions/release-please-action/raw/main/screen.png)

## Releasing A Protocol Version

To release:
* Create a PR following the conventions stated in the above section.
* Merge this PR in.
* If the conventional commit convention is followed, CI will create a bump version PR with release notes and the new version. The new version follows conventional commit semantics.
* Double check the new version by looking at the .env file.
* Merge the bump version PR in.
* On merge to master, CI will detect the version change and publish a Java JAR, a PyPi package and an NPM package. A Github release is also created.

## Release Errors

If there is a publishing error on merge to master, the [manual publishing workflow](https://github.com/airbytehq/airbyte-protocol/actions/workflows/manual-publish.yml) is an escape hatch for manually republishing artifacts for a specific version.
