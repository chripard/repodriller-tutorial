package tutorial.q3;

import java.util.Map;

import br.com.metricminer2.MetricMiner2;
import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Q3Study implements Study {

	public static void main(String[] args) {
		new MetricMiner2().start(new Q3Study());
	}
	
	@Override
	public void execute() {
		ModificationsPerFileVisitor visitor = new ModificationsPerFileVisitor();
		
		new RepositoryMining()
			.in(GitRepository.singleProject("/Users/mauricioaniche/Desktop/tutorial/jfreechart-fse"))
			.through(Commits.all())
			.process(visitor)
			.withThreads(3)
			.mine();
		
		CSVFile csv = new CSVFile("/Users/mauricioaniche/Desktop/tutorial/q3.csv");
		for(Map.Entry<String, Integer> k : visitor.getFiles().entrySet()) {
			csv.write(
				k.getKey(),
				k.getValue()
			);
		}
	}

}
