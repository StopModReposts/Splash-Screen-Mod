## How to embed this Mod into your own?
All you need to embed this mod into yours is to add a few things to your build.gradle file
```GRADLE
configurations {
    embed
    compile.extendsFrom(embed)
}

repositories {
	maven {
		url = "https://mvn.stopmodreposts.org/"
	}
}

dependencies {
	embed "org.stopmodreposts:stopmodreposts:${mc_version}-${stopmodreposts_version}"
}
```
And add this to your gradle.properties file (the bottom one is required till we've released a first version)
```GRADLE
stopmodreposts_version=1.0.0
//stopmodreposts_version=1.0.0-SNAPSHOT
```