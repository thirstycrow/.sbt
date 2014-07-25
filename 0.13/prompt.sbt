import com.typesafe.sbt.SbtGit.GitKeys

shellPrompt := { state =>
  val userHome = System.getProperty("user.home")
  val extracted = Project.extract(state)
  import extracted._
  val reader = extracted get GitKeys.gitReader
  val unitPath = currentUnit.localBase.getPath.replaceAll(s"^${userHome}", "~")
  val relativePath = if (currentUnit.localBase == currentProject.base) {
    ""
  } else {
    val relativePath = IO.relativize(currentUnit.localBase, currentProject.base)
    relativePath.get.replaceAll("^(?=[^/])", "/")
  }
  val branch = Option(reader.withGit(_.branch))
  import scala.Console._
  def section(text: String, color: String, bold: String = "") = s"${color}${bold}${text}${RESET}"
  val sections = Seq(
    "\n",
    section(currentUnit.root, YELLOW, BOLD),
    section(" in ", WHITE),
    section(currentUnit.localBase.getPath.replaceAll(s"^${userHome}", "~"), GREEN, BOLD)
  )
  val gitSections = branch.map(b => Seq(
    section(" on ", WHITE),
    section(b, CYAN, BOLD)
  )).getOrElse(Nil)
  val projectSections = Seq(
    "\n",
    section(currentProject.id + "> ", WHITE, BOLD)
  )
  (sections ++ gitSections ++ projectSections).mkString
}
