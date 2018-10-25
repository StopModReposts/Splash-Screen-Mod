## What does 'StopModReposts' do?
It adds this beautiful GUI, in order to educate more users about the problem of mod reposting sites.
And don't worry, it does only show up once per Minecraft Directory. (once per installation location)</br>
Disclaimer: The screenshot doesn't showcase the latest text.</br>
![Preview Image](https://raw.githubusercontent.com/StopModReposts/Splash-Screen-Mod/master/preview.png)
The Goal of this project isn't to accuse people of something, but rather to educate them.</br>
That a person downloaded the file from curseforge, doesn't mean that it knows what repost sites are.


## How can I help as a Modder?
If you wanna help just embed* this mod into your mod (it's not a dependency).
The more players read this, the better our chances are.

* Embeding the mod means that the mod jar will be put into your mod jar, and forge will extract this mod.

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
            'ContainedDeps': configurations.embedNoCompile.collect { it.getName() }.join(' ')
		])
	}
}
```
(Note: we are using `embedNoCompile` because you don't want to use any of the mods classes, unlike with most things you wanna embed)
And add this to your gradle.properties file
```GRADLE
stopmodreposts_version=1.0.0
```

## How can I help as a User?
To be continued