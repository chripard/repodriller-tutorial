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
	
	@Override
	public void execute() {
		new RepositoryMining()
			.in(GitRepository.singleProject("/Users/mauricioaniche/Desktop/tutorial/jfreechart-fse"))
			.through(Commits.range("bf03fad60a26b27263fc5be23336eabd892f7e59", "5453efc2f0295bc4e36b161982297003da01c14f"))
			.process(new CommitsWithBugVisitor(), new CSVFile("/Users/mauricioaniche/Desktop/tutorial/q2.csv"))
			.mine();
	}

}
