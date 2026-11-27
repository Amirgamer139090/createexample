# Create: Power up!

An interconnected technology addon for Minecraft — built for **NeoForge 1.21.1**. The long-term goal is a quality-manufacturing and computer layer on top of Create: machines, power networks, modular computers, CPUs/RAM/GPU/storage, BIOS, a virtual filesystem, a desktop OS, and scripted media — see [docs/MASTER_DESIGN.md](docs/MASTER_DESIGN.md).

[![Build](https://github.com/Amirgamer139090/createexample/actions/workflows/build.yml/badge.svg?branch=create-power-up)](https://github.com/Amirgamer139090/createexample/actions/workflows/build.yml)

## Status

This repository is at **Stage 00** of the implementation plan (repository audit / baseline setup). The mod compiles and produces a loadable jar; gameplay features arrive stage by stage via the prompts in [prompts/](prompts/). Progress is tracked in [DEVELOPMENT_STATUS.md](DEVELOPMENT_STATUS.md) and [docs/PROGRESS.md](docs/PROGRESS.md).

## Tech stack

| Component | Version |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.235 |
| Gradle plugin | `net.neoforged.gradle.userdev` 7.1.38 |
| Gradle | 8.14.3 |
| Java | 21 (toolchain) |
| Mappings | Mojang official |

## Building

Prerequisites: JDK 21 and an internet connection (first build downloads Minecraft and NeoForge artifacts, decompiles them, and takes several minutes).

```bash
gradle build          # if Gradle 8.14.3 is installed locally
gradle wrapper        # one-time: generate gradlew / gradlew.bat / wrapper jar
./gradlew build       # afterwards use the wrapper
```

The compiled mod jar lands in `build/libs/create_powerup-0.1.0.jar`. Every push to the `create-power-up` branch (and to `main`) triggers the [Build workflow](.github/workflows/build.yml), which compiles the mod on GitHub Actions and uploads the jar as an artifact — no local toolchain required.

## Repository layout

```
src/main/java/com/powerup/      Mod sources (entry point: CreatePowerUp.java)
src/main/resources/             Mod metadata, assets, lang files (en/fa)
gradle/wrapper/                 Gradle wrapper configuration
docs/                           Design documents (MASTER_DESIGN, ARCHITECTURE, IMPLEMENTATION_PLAN, CODING_RULES, ...)
prompts/                        Stage-by-stage implementation prompts (00-15)
AGENTS.md                       Working agreement for AI implementation agents
.github/workflows/              CI: build on push/PR
```

## Development notes

- `AGENTS.md` is the operating agreement for AI agents implementing stages: one approved stage per request, ZIP delivery, no feature skipping.
- The Create dependency is intentionally **not** declared yet — its exact compatible artifact/version is being verified before integration (Stage 14). Do not add guessed coordinates.
- Documentation is also available in Persian: [README_FA.md](README_FA.md).

## License

All Rights Reserved — © Power up! Team
