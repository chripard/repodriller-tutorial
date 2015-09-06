package tutorial.q1;

import br.com.metricminer2.MetricMiner2;
import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Q1Study implements Study {

	public static void main(String[] args) {
		new MetricMiner2().start(new Q1Study());
	}
	
	@Override
	public void execute() {
		new RepositoryMining()
			.in(GitRepository.singleProject("/Users/mauricioaniche/workspace/metricminer2-tutorial/project/jfreechart"))
			.through(Commits.all())
			.process(new DevelopersVisitor(), new CSVFile("/Users/mauricioaniche/workspace/metricminer2-tutorial/project/q1.csv"))
			.mine();
	}

}
