package tutorial.q1;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

public class Q1Study implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new Q1Study());
	}
	
	//@Override
	public void execute() {
		new RepositoryMining()
			.in(GitRepository.singleProject("C:/Users/dev/Documents/GitHub/repodriller-tutorial"))
			.through(Commits.all())
			.process(new DevelopersVisitor(), new CSVFile("/Users/dev/Desktop/tutorial/q1.csv"))
			.mine();
	}

}
