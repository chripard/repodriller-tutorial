package tutorial.q6;

import br.com.metricminer2.MetricMiner2;
import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Q6Study implements Study {

	public static void main(String[] args) {
		new MetricMiner2().start(new Q6Study());
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
