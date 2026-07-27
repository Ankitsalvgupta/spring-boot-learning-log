# CakeBaker

A small Spring Boot project demonstrating **Dependency Injection (DI)** using constructor injection and `@Qualifier` to resolve multiple implementations of the same interface.

## What it does

`CakeBaker` depends on two abstractions — `Frosting` and `Syrup` — each with two concrete flavors (Chocolate and Strawberry). Spring injects the chosen implementations into `CakeBaker` at startup, and `bakeCake()` is invoked automatically when the application runs.

## Project structure

```
src/main/java/com/ankit/cakebaker/CakeBaker/
├── CakeBakerApplication.java        # Entry point; runs bakeCake() on startup via CommandLineRunner
├── model/
│   ├── Frosting.java                 # Interface: getFrostingType()
│   └── Syrup.java                    # Interface: getSyrupType()
├── impl/
│   ├── chocolate/
│   │   ├── ChocolateFrosting.java     # @Component @Qualifier("chocoFrost")
│   │   └── ChocolateSyrup.java        # @Component @Qualifier("chocoSyrup")
│   └── strawberry/
│       ├── StrawberryFrosting.java    # @Component @Qualifier("strawFrost")
│       └── StrawberrySyrup.java       # @Component @Qualifier("strawSyrup")
└── service/
    └── CakeBaker.java                # @Service; depends on Frosting + Syrup, exposes bakeCake()
```

## How Dependency Injection is used

- `Frosting` and `Syrup` each have two implementations, so Spring can't auto-wire one without help.
- `@Qualifier("...")` on each implementation, matched by `@Qualifier("...")` on the `CakeBaker` constructor parameters, tells Spring exactly which bean to inject.
- `CakeBaker` uses **constructor injection** (the recommended approach — dependencies are explicit, fields can be `final`, and it's easy to unit test).
- `CakeBakerApplication` implements `CommandLineRunner` and has `CakeBaker` injected into its own constructor. Spring Boot automatically calls `run()` after the application context starts, which in turn calls `cakeBaker.bakeCake()`.

Currently wired to **Chocolate** frosting + **Chocolate** syrup. To switch flavors, change the `@Qualifier` values in `CakeBaker`'s constructor to `"strawFrost"` / `"strawSyrup"`.

## Requirements

- Java 21
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper)

## Running the project

```bash
./mvnw spring-boot:run
```

On Windows:
```bash
mvnw.cmd spring-boot:run
```

### Expected output

```
Baking cake with frosting type: Chocolate Frosting
Baking cake with syrup type: Chocolate Syrup
```

## Tech stack

- Spring Boot 4.1.0
- Spring Framework 7
- Java 21
- Maven
