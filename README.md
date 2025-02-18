# A demo project to showcase GitHub Artifact Attestations

> Artifact attestations enable you to create unfalsifiable provenance and integrity guarantees for the software you build. In turn, people who consume your software can verify where and how your software was built. This demo project showcases how to use GitHub Artifact Attestation to sign and verify artifacts for a Maven project.

- [Using artifact attestations](https://docs.github.com/enterprise-cloud@latest/actions/security-for-github-actions/using-artifact-attestations)
- [Artifact Attestations now support multiple subjects](https://github.blog/changelog/2024-12-04-artifact-attestations-now-support-multiple-subjects/)
- [Configure GitHub Artifact Attestations for secure cloud-native delivery](https://github.blog/security/supply-chain-security/configure-github-artifact-attestations-for-secure-cloud-native-delivery/)
- [Artifact Attestations is generally available](https://github.blog/changelog/2024-06-25-artifact-attestations-is-generally-available/)
- [Introducing Artifact Attestations–now in public beta](https://github.blog/news-insights/product-news/introducing-artifact-attestations-now-in-public-beta/)

This project uses the consistgen for the pupose of this demo, a simple Java library that provides static and dynamic implementations for generating timestamps, random strings, and UUIDs, offering a structured way to inject predictable data into test cases.