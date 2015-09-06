package tutorial.q4;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.RepositoryFile;
import br.com.metricminer2.scm.SCMRepository;
import tutorial.utils.Utils;

public class LinesOfCodeVisitor implements CommitVisitor {

	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		try {
			repo.getScm().checkout(commit.getHash());
			List<RepositoryFile> files = repo.getScm().files();

			int totalLoc = 0;
			
			for(RepositoryFile file : files) {
				if(!file.fileNameEndsWith("java")) continue;
				
				File soFile = file.getFile();
				int loc = Utils.countLineNumbers(Utils.readFile(soFile));
				totalLoc += loc;
				
			}
			
			writer.write(
				new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(commit.getDate().getTime()),
				commit.getHash(),
				totalLoc
			);
			
		} finally {
			repo.getScm().reset();
		}

	}


	@Override
	public String name() {
		return "loc-per-commit";
	}

}
