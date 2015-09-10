package tutorial.q2;

import br.com.metricminer2.MetricMiner2;
import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Q2Study implements Study {

	public static void main(String[] args) {
		new MetricMiner2().start(new Q2Study());
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
