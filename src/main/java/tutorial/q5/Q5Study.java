package tutorial.q5;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

public class Q5Study implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new Q5Study());
	}
	
	@Override
	public void execute() {
		CSVFile csv = new CSVFile("/Users/mauricioaniche/Desktop/tutorial/q6.csv");
		DiffVisitor visitor = new DiffVisitor();
		
		new RepositoryMining()
			.in(GitRepository.singleProject("/Users/mauricioaniche/Desktop/tutorial/jfreechart-fse"))
			.through(Commits.all())
			.process(visitor, csv)
			.mine();
		
	}

}
