# Splash-Screen Mod
[Forge Version](https://github.com/StopModReposts/Splash-Screen-Mod) - [Fabric Version](https://github.com/StopModReposts/Splash-Screen-Mod-Fabric)

This mod helps modders to bring attention to players about the StopModReposts campaign.

---
# Forge Version

## What does 'StopModReposts' do?
It adds this beautiful GUI, in order to educate more users about the problem of mod reposting sites.
And don't worry, it does only show up once per Minecraft Directory. (Once per installation location)</br>
![Preview Image](https://raw.githubusercontent.com/StopModReposts/Splash-Screen-Mod/master/preview.png)</br>
The Goal of this project isn't to accuse people of something, but rather to educate them.</br>
That a person downloaded the file from curseforge, doesn't mean that it knows what repost sites are.

## How can I help as a Modder?
If you wanna help just embed* this mod into your mod.
The more players read this, the better our chances are.

* Embeding the mod means that the mod jar will be put into your mod jar, and forge will extract this mod.

### How to embed this Mod?
The first thing you need to do is build the mod yourself, as it doesn't have a maven repo yet:
```
gradlew build
```

After that you need to embed the mod into your own mod. To do so add this to your build.gradle file:
```GRADLE
jarJar.enable()

repositories {
    flatDir {
        dir 'libs'
    }
}

dependencies {
    implementation fg.deobf("org.stopmodreposts:stopmodreposts:1.0")
    jarJar(group: 'org.stopmodreposts', name: 'stopmodreposts', version: '[1.0,1.1)')
}
```
The ```jarJar.enable``` can be anywhere in the build script but I would suggest to put it under ```java.toolchain.languageVersion```</br>
(Note: the text you add in the ```dependencies``` block *needs* to be under ```net.minecraftforge``` to work!)</br>
Now you just need to refresh your gradle project and you're good to go:
```
gradlew --refresh-dependencies
gradlew genEclipseRuns / genIntellijRuns
gradlew eclipse / intellij
```
That's it! Thanks for letting the users of your mod know about StopModReposts!

## How can I help as a User?
Go on our [website](https://stopmodreposts.org) and download our [extension](https://stopmodreposts.org/extension) to be safe from mod-reposting sites in the future.
