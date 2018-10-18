## What does 'StopModReposts' do?
It adds this beautiful GUI, in order to educate more users about the problem of mod reposting sites.
![Preview Image](https://raw.githubusercontent.com/StopModReposts/Splash-Screen-Mod/master/preview.png)

## How can I help as a Modder?
If you wanna help just embed this mod into your mod (it's not a dependency).
The more players read this, the better our chances are.

### How to embed this Mod?
All you need to embed this mod into yours is to add a few things to your build.gradle file
```GRADLE
configurations {
    embedNoCompile
}

repositories {
	maven {
		url = "https://mvn.stopmodreposts.org/"
	}
}

dependencies {
	embedNoCompile "org.stopmodreposts:stopmodreposts:${mc_version}-${stopmodreposts_version}"
}

jar{
	into('META-INF/libraries'){
		from configurations.embedNoCompile
	}

	manifest {
		attributes([
            'ContainedDeps': configurations.embedNoCompile.collect { it.getName() }.join(' '),
			'Maven-Artifact': "${project.group}:${project.archivesBaseName}:${project.version}",
			'Timestamp': System.currentTimeMillis()
		])
	}
}
```
(Note: we are using `embedNoCompile` because you don't want to use any of the mods classes, unlike with most things you wanna embed)
And add this to your gradle.properties file (the bottom one is required till we've released a first version)
```GRADLE
stopmodreposts_version=1.0.0
//stopmodreposts_version=1.0.0-SNAPSHOT
```

## How can I help as a User?
To be continued