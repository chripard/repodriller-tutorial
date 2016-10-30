package tutorial.q4;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

public class Q4Study implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new Q4Study());
	}
	
	@Override
	public void execute() {
		new RepositoryMining()
			.in(GitRepository.singleProject("/Users/mauricioaniche/Desktop/tutorial/jfreechart-fse"))
			.through(Commits.monthly(6))
			.process(new LinesOfCodeVisitor(), new CSVFile("/Users/mauricioaniche/Desktop/tutorial/q4.csv"))
			.mine();
	}

}
