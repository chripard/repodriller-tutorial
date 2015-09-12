package tutorial.q5;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.parser.jdt.JDTRunner;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.RepositoryFile;
import br.com.metricminer2.scm.SCMRepository;
import tutorial.utils.Utils;

public class CCVisitor implements CommitVisitor {

	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		
		try {
			repo.getScm().checkout(commit.getHash());
			List<RepositoryFile> files = repo.getScm().files();

			int totalCc = 0;
			
			for(RepositoryFile file : files) {
				if(!file.fileNameEndsWith("java")) continue;
				
				File soFile = file.getFile();
				
				
				JDTCCListener visitor = new JDTCCListener();
				new JDTRunner().visit(visitor, new ByteArrayInputStream(Utils.readFile(soFile).getBytes()));

				totalCc = visitor.getCc();
			}
			
			writer.write(
				new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(commit.getDate().getTime()),
				commit.getHash(),
				totalCc
			);
			
		} finally {
			repo.getScm().reset();
		}
		
	}

	@Override
	public String name() {
		return "cc";
	}

}
