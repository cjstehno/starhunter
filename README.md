# Star Hunter

Simple alien-blasting game used to learn 2D game development.

See the [GitHub project](https://github.com/cjstehno/starhunter) or [wiki](https://github.com/cjstehno/starhunter/wiki) for more information.

## Building

You can build the project using the Gradle wrapper. Simply down load the source and run:

```gradlew build unpackNatives -x test```

Once the project has been built once (and native unpacked) you can run the tests as normal:

```gradlew test```

## Installer

You can build the installer using:

```gradlew installApp izPackCreateInstaller```

which will create an `-installer` distribution which can be distributed and run as a standalone installer (currently works for Windows only).

## Running

To run the built project (and start the game):

```gradlew run```

or if using an installed distribution, you can run the `bin/starhunter.bat` file to start the game.

> Note: this is not a production ready game, nor will it ever be - it's for educational purposes only.

