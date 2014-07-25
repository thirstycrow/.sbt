credentials ++= (Path.userHome / ".sbt" / "credentials").listFiles.filter(_.isFile).toSeq
