package tutorial.q2;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

public class Q2Study implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new Q2Study());
	}
	
	//@Override
	public void execute() {
		new RepositoryMining()
			.in(GitRepository.singleProject("C:/Users/dev/Documents/GitHub/repodriller-tutorial"))
			.through(Commits.range("5b74b0b834619d099529fb8a176d2aa0019e9fb3", "03743da235b7e3064ab334278d012d819d78d2be"))
			.process(new CommitsWithBugVisitor(), new CSVFile("/Users/dev/Desktop/tutorial/q2.csv"))
			.mine();
	}

}
