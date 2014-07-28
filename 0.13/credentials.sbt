credentials ++= (Path.userHome / ".sbt" / "credentials")
  .listFiles
  .collect {
    case f if f.isFile => Credentials(f)
  }
  .toSeq
