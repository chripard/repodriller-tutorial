package tutorial.q3;

import java.util.Map;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

public class Q3Study implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new Q3Study());
	}
	
	//@Override
	public void execute() {
		ModificationsPerFileVisitor visitor = new ModificationsPerFileVisitor();
		
		new RepositoryMining()
			.in(GitRepository.singleProject("C:/Users/dev/Documents/GitHub/repodriller-tutorial"))
			.through(Commits.all())
			.process(visitor)
			.withThreads(3)
			.mine();
		
		CSVFile csv = new CSVFile("/Users/dev/Desktop/tutorial/q3.csv");
		for(Map.Entry<String, Integer> k : visitor.getFiles().entrySet()) {
			csv.write(
				k.getKey(),
				k.getValue()
			);
		}
	}

}
