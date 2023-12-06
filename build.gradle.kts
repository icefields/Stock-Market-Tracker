buildscript {
    extra.apply{
        set("compose_version", "1.5.4")
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false

}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
