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
	
	//@Override
	public void execute() {
		CSVFile csv = new CSVFile("/Users/dev/Desktop/tutorial/q6.csv");
		DiffVisitor visitor = new DiffVisitor();
		
		new RepositoryMining()
			.in(GitRepository.singleProject("C:/Users/dev/Documents/GitHub/repodriller-tutorial"))
			.through(Commits.all())
			.process(visitor, csv)
			.mine();
		
	}

}
