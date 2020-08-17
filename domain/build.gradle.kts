plugins {
    id(Deps.Plugins.JavaLib)
    id(Deps.Plugins.Kotlin)
}

dependencies {
    implementation(fileTree(Deps.Common.FileTree))
    implementation(Deps.Kt.StdJdk)
    api(Deps.Koin.Core)
    api(Deps.Common.Timber)
}