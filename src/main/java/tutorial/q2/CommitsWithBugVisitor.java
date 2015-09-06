package tutorial.q2;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.SCMRepository;

public class CommitsWithBugVisitor implements CommitVisitor {

	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		
		boolean containsABug = commit.getMsg().contains("bug");
		
		writer.write(
			commit.getHash(),
			containsABug
		);

	}

	@Override
	public String name() {
		return "commits-with-bugs";
	}

}
