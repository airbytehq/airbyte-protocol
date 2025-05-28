# Changelog

## [0.17.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.16.0...v0.17.0) (2025-05-28)


### Features

* remove v1 models ([#133](https://github.com/airbytehq/airbyte-protocol/issues/133)) ([d888b19](https://github.com/airbytehq/airbyte-protocol/commit/d888b194787f984ddc6d6018fd45ae92c9ef97b4))

## [0.16.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.15.0...v0.16.0) (2025-05-26)


### Features

* add destination_catalog message ([#130](https://github.com/airbytehq/airbyte-protocol/issues/130)) ([1ceab1e](https://github.com/airbytehq/airbyte-protocol/commit/1ceab1e07fc03660543c05c8aa86efee94c62850))
* rename schema to json-schema ([#134](https://github.com/airbytehq/airbyte-protocol/issues/134)) ([23ef590](https://github.com/airbytehq/airbyte-protocol/commit/23ef590351815bd6308939ba701ecbcafd0d2897))

## [0.15.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.14.5...v0.15.0) (2025-04-07)


### Features

* add support for file+metadata streams ([#119](https://github.com/airbytehq/airbyte-protocol/issues/119)) ([b4b396a](https://github.com/airbytehq/airbyte-protocol/commit/b4b396a288015fa105d62c35a935fff825ac42c1))

## [0.14.5](https://github.com/airbytehq/airbyte-protocol/compare/v0.14.4...v0.14.5) (2025-04-04)


### Bug Fixes

* "fix: remove V1 models" ([#125](https://github.com/airbytehq/airbyte-protocol/issues/125)) ([9cbdde0](https://github.com/airbytehq/airbyte-protocol/commit/9cbdde05d686bbc3b5e4d1fb4146b105fbdade72))

## [0.14.4](https://github.com/airbytehq/airbyte-protocol/compare/v0.14.3...v0.14.4) (2025-04-03)


### Bug Fixes

* remove V1 models. Move clearly schema defined objects into V0 package. ([#120](https://github.com/airbytehq/airbyte-protocol/issues/120)) ([c73ef4e](https://github.com/airbytehq/airbyte-protocol/commit/c73ef4e04a2c9b31610dd103e9ef5aa45e6d5b49))

## [0.14.3](https://github.com/airbytehq/airbyte-protocol/compare/v0.14.2...v0.14.3) (2025-03-28)


### Bug Fixes

* equalise V0 and V1. ([#118](https://github.com/airbytehq/airbyte-protocol/issues/118)) ([577b40a](https://github.com/airbytehq/airbyte-protocol/commit/577b40a694fcceda0259eacaf9075b755b703d7e))
* use .env file with Gradle ([#112](https://github.com/airbytehq/airbyte-protocol/issues/112)) ([d759d02](https://github.com/airbytehq/airbyte-protocol/commit/d759d0225c2b1f486c6be57bdd1fcb84403b990a))

## [0.14.2](https://github.com/airbytehq/airbyte-protocol/compare/v0.14.1...v0.14.2) (2025-01-23)


### Bug Fixes

* (DeclarativeOAuthFlow) - make the `extract_output` Optional; correct the descriptions ([#106](https://github.com/airbytehq/airbyte-protocol/issues/106)) ([94b0198](https://github.com/airbytehq/airbyte-protocol/commit/94b019840a573ad4e525409238b553b505d31041))

## [0.14.1](https://github.com/airbytehq/airbyte-protocol/compare/v0.14.0...v0.14.1) (2024-11-27)


### Bug Fixes

* (DeclarativeOAuthFlow) - add `access_token_params` property to match the actual spec properties ([#103](https://github.com/airbytehq/airbyte-protocol/issues/103)) ([b47f0ca](https://github.com/airbytehq/airbyte-protocol/commit/b47f0ca0c14137a29cd7e8b716925970a824f6e1))

## [0.14.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.13.1...v0.14.0) (2024-11-06)


### Features

* add connector `DeclarativeOAuthFlow` input to protocol ([#96](https://github.com/airbytehq/airbyte-protocol/issues/96)) ([32c58c8](https://github.com/airbytehq/airbyte-protocol/commit/32c58c8aa7408a7f91703fbf6aa2badfccf84182))

## [0.13.1](https://github.com/airbytehq/airbyte-protocol/compare/v0.13.0...v0.13.1) (2024-10-15)


### Bug Fixes

* fix build issues with plugin versions not compatible with java 21 ([#99](https://github.com/airbytehq/airbyte-protocol/issues/99)) ([226ac3b](https://github.com/airbytehq/airbyte-protocol/commit/226ac3b5a3c0c15014c6ceb190a1f4ed957eac76))
* upgrade from java 17 to 21 and from gradle 7.6 to 8.10 ([#97](https://github.com/airbytehq/airbyte-protocol/issues/97)) ([6164fc0](https://github.com/airbytehq/airbyte-protocol/commit/6164fc07a51cdfe53fb40f20ab7b13ef0d5d62d6))

## [0.13.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.12.2...v0.13.0) (2024-08-23)


### Features

* publish new dataclasses version of python protocol, `airbyte_protocol_models_dataclasses` to pypi ([#91](https://github.com/airbytehq/airbyte-protocol/issues/91)) ([5aa44db](https://github.com/airbytehq/airbyte-protocol/commit/5aa44dbacca1200621008356cec0280e42c39a9a))

## [0.12.2](https://github.com/airbytehq/airbyte-protocol/compare/v0.12.1...v0.12.2) (2024-06-18)


### Bug Fixes

* fix publishing of airbyte-protocol-models-pdv2 ([#85](https://github.com/airbytehq/airbyte-protocol/issues/85)) ([55dd53a](https://github.com/airbytehq/airbyte-protocol/commit/55dd53aae29429cfecd6f1112f9951446efddbd0))

## [0.12.1](https://github.com/airbytehq/airbyte-protocol/compare/v0.12.0...v0.12.1) (2024-06-18)


### Bug Fixes

* fix publish for python classes calling wrong docker script ([#83](https://github.com/airbytehq/airbyte-protocol/issues/83)) ([2a92e61](https://github.com/airbytehq/airbyte-protocol/commit/2a92e616c00d7ecd1e0c06ef09453c02034c21a5))

## [0.12.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.11.0...v0.12.0) (2024-06-17)


### Features

* publish new pydantic v2 version of python protocol, [`airbyte-protocol-models-pdv2`](https://pypi.org/project/airbyte-protocol-models/) to pypi, temporarily alongside pydantic v1 version ([#81](https://github.com/airbytehq/airbyte-protocol/issues/81)) ([9334f94](https://github.com/airbytehq/airbyte-protocol/commit/9334f94629805d1f6b3b99d5030a37d9e22bebcc))

## [0.11.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.10.0...v0.11.0) (2024-05-16)


### Features

* add reasons to AirbyteStreamStatusTraceMessage ([#74](https://github.com/airbytehq/airbyte-protocol/issues/74)) ([fe0ada2](https://github.com/airbytehq/airbyte-protocol/commit/fe0ada2f4758b83a83829299436e8afa19a3d090))

## [0.10.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.9.0...v0.10.0) (2024-05-14)


### Features

* Introduce is_resumable. ([#73](https://github.com/airbytehq/airbyte-protocol/issues/73)) ([0ed3e7a](https://github.com/airbytehq/airbyte-protocol/commit/0ed3e7ae815604ee6af39926277264fe036b7081))

## [0.9.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.8.0...v0.9.0) (2024-04-02)


### Features

* Proposal for refreshes needed metadata ([#67](https://github.com/airbytehq/airbyte-protocol/issues/67)) ([7e8a487](https://github.com/airbytehq/airbyte-protocol/commit/7e8a487603af231e29e3031ca631a9f54a7c366a))

## [0.8.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.7.0...v0.8.0) (2024-03-19)


### Features

* add transient_error as new failure type ([#69](https://github.com/airbytehq/airbyte-protocol/issues/69)) ([8fada32](https://github.com/airbytehq/airbyte-protocol/commit/8fada32aedb559731c62f0e35443e4e6dfd3d600))

## [0.7.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.6.0...v0.7.0) (2024-03-06)


### Features

* Add DESTINATION_TYPECAST_ERROR to reason enum value ([#65](https://github.com/airbytehq/airbyte-protocol/issues/65)) ([86f03e9](https://github.com/airbytehq/airbyte-protocol/commit/86f03e9259c5e03b767a28400080ad63e91e1b29))

## [0.6.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.5.3...v0.6.0) (2024-01-24)


### Features

* `AirbyteRecordMessageMeta` for per-record lineage and changes ([#56](https://github.com/airbytehq/airbyte-protocol/issues/56)) ([45461e1](https://github.com/airbytehq/airbyte-protocol/commit/45461e133e001a533c6fb01a7aa26e94dc0f13d2))

## [0.5.3](https://github.com/airbytehq/airbyte-protocol/compare/v0.5.2...v0.5.3) (2024-01-04)


### Bug Fixes

* Add py.typed to python distribution ([#57](https://github.com/airbytehq/airbyte-protocol/issues/57)) ([212095d](https://github.com/airbytehq/airbyte-protocol/commit/212095d554c8fef0b2389129173de11cf032e670))

## [0.5.2](https://github.com/airbytehq/airbyte-protocol/compare/v0.5.1...v0.5.2) (2023-12-26)


### Bug Fixes

* Removed unused V1. ([#58](https://github.com/airbytehq/airbyte-protocol/issues/58)) ([24be395](https://github.com/airbytehq/airbyte-protocol/commit/24be3958a2685def17c07992fbe87f6ac9867af7))

## [0.5.1](https://github.com/airbytehq/airbyte-protocol/compare/v0.5.0...v0.5.1) (2023-12-08)


### Bug Fixes

* only add user selected streams to the catalog diff ([#53](https://github.com/airbytehq/airbyte-protocol/issues/53)) ([5360c07](https://github.com/airbytehq/airbyte-protocol/commit/5360c073eca0130bd76c59f76ffd5ae4516e2c19))

## [0.5.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.4.2...v0.5.0) (2023-11-13)


### Features

* add `AirbyteStateStats` ([#49](https://github.com/airbytehq/airbyte-protocol/issues/49)) ([a7ac0cf](https://github.com/airbytehq/airbyte-protocol/commit/a7ac0cf2e9b52bf1c8cc724464d9fb0e012e44c5))

## [0.4.2](https://github.com/airbytehq/airbyte-protocol/compare/v0.4.1...v0.4.2) (2023-10-16)


### Bug Fixes

* Add more documentation to analytics message ([#46](https://github.com/airbytehq/airbyte-protocol/issues/46)) ([c181fb4](https://github.com/airbytehq/airbyte-protocol/commit/c181fb42b72172dc3fcae85f6786fcb6d6153281))

## [0.4.1](https://github.com/airbytehq/airbyte-protocol/compare/v0.4.0...v0.4.1) (2023-08-14)


### Bug Fixes

* Python package - relax pydantic depenency version requirement ([#41](https://github.com/airbytehq/airbyte-protocol/issues/41)) ([69bf066](https://github.com/airbytehq/airbyte-protocol/commit/69bf066050ff8081bc8fcafcb5410773867df896))
* Skip dont error when invalid jsonSchemaNode is found during traversal ([#44](https://github.com/airbytehq/airbyte-protocol/issues/44)) ([e1fa4b7](https://github.com/airbytehq/airbyte-protocol/commit/e1fa4b78873f59f4cb04b127df0944b89ce88b24))

## [0.4.0](https://github.com/airbytehq/airbyte-protocol/compare/v0.3.6...v0.4.0) (2023-08-01)


### Bug Fixes

* Python package - relax pydantic dependency version requirement ([#41](https://github.com/airbytehq/airbyte-protocol/issues/41)) ([69bf066](https://github.com/airbytehq/airbyte-protocol/commit/69bf066050ff8081bc8fcafcb5410773867df896))

### Refactors

* Remove deprecated authSpecification from connector specification ([#39](https://github.com/airbytehq/airbyte-protocol/issues/39)) ([029148b](https://github.com/airbytehq/airbyte-protocol/commit/029148b42d35ab441c11a8845927f44ef118b8c0))

## [0.3.6](https://github.com/airbytehq/airbyte-protocol/compare/v0.3.5...v0.3.6) (2023-04-20)


### Bug Fixes

* Remove success in favor of explicit status enum values ([#34](https://github.com/airbytehq/airbyte-protocol/issues/34)) ([046c4e2](https://github.com/airbytehq/airbyte-protocol/commit/046c4e21b7e687bed64d2303a6c71d1137aed3a9))

## [0.3.5](https://github.com/airbytehq/airbyte-protocol/compare/v0.3.4...v0.3.5) (2023-04-13)


### Bug Fixes

* Fix indentation ([#30](https://github.com/airbytehq/airbyte-protocol/issues/30)) ([a3a034f](https://github.com/airbytehq/airbyte-protocol/commit/a3a034f02528d95a3955a9883088431fde383a4d))

## [0.3.4](https://github.com/airbytehq/airbyte-protocol/compare/v0.3.3...v0.3.4) (2023-04-13)


### Bug Fixes

* Fix indentation ([#28](https://github.com/airbytehq/airbyte-protocol/issues/28)) ([8a87cc9](https://github.com/airbytehq/airbyte-protocol/commit/8a87cc9afb6247d15f03cb8574386c069998309d))

## [0.3.3](https://github.com/airbytehq/airbyte-protocol/compare/v0.3.2...v0.3.3) (2023-04-12)


### Features

* Add stream status trace message ([#18](https://github.com/airbytehq/airbyte-protocol/issues/18)) ([838b7ac](https://github.com/airbytehq/airbyte-protocol/commit/838b7ac381b5539bf207c993e22aafba85e90c99))
