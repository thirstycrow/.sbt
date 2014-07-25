PublishToThem.publishableRepos ++= Map(
  "gh-pages" -> {
    val userHome = System.getProperty("user.home")
    Resolver.file("gh-pages", file(userHome) / "Projects" / "repo")
  },
  "artifactoryLocal" -> {
    val artifactoryLocal = "http://127.0.0.1:8081/artifactory"
    if (version.value endsWith "SNAPSHOT")
      "artifactory-local-snapshots" at artifactoryLocal + "/libs-snapshot-local"
    else
      "artifactory-local-release" at artifactoryLocal + "/libs-release-local"
  }
)
