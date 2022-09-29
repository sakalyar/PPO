package serie02.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class StdSplitManager implements SplitManager {

	File f;
	long sizeInOctects;
	String path;
	long n;
	InputStream in;
	OutputStream out;
	
	long gSS;
	long lg;
	
	long sizesArray[];
	
	public StdSplitManager() {
		f = getFile();
		sizeInOctects = f.length();
	}
	public StdSplitManager(File file) {
		changeFor(f);
		path = file.getAbsolutePath();
		changeFor(file);
	}

	
	// 
	@Override
	public boolean canSplit() {
		// TODO Auto-generated method stub
		if (f.exists() && f.getAbsolutePath() != null && f.length() > 0 && !f.isDirectory())
			return true;
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile() {
		String address = f.getAbsolutePath();
		f = new File(address);
		in = null;
		out = null;
		return f;
	}

	@Override
	public int getMaxFragmentNb() {
		int m = (int) Math.max(
				1, Math.ceil((double) getFile().length() / MIN_FRAGMENT_SIZE));
		return Math.min(MAX_FRAGMENT_NB, m);
		//return 0;
	}

	@Override
	public String[] getSplitsNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long[] getSplitsSizes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeFor(File f) {
		this.f = new File(f.getAbsolutePath());
		try {
			String origPath = f.getCanonicalPath();
			String destPath = origPath.replace(f.getName(),"");
		    String destFile = f.getName();
		    String n_destFile = destFile.replace("-a", "");
		    this.f = new File(destPath+n_destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (f.exists()) { {
			//f.close();
		}
		}
	}

	@Override
	public void setSplitsSizes(long size) {
		// TODO Auto-generated method stub
		if (canSplit() && size >= Math.ceil(getFile().length() / getMaxFragmentNb())) {
			gSS = (long) Math.ceil(getFile().length() / size);
		} 
	}

	@Override
	public void setSplitsSizes(long[] sizes) {
		int lengthOfSizes = sizes.length;
		long sumOfPassedLengths = Arrays.stream(sizes).sum();
		lg = getFile().length();
		
		if (canSplit() && lengthOfSizes > 0 &&
				((sumOfPassedLengths - sizes[lengthOfSizes-1] < lg && lengthOfSizes < getMaxFragmentNb()) ||
				(sumOfPassedLengths - sizes[lengthOfSizes-1] >= lg && lengthOfSizes <= getMaxFragmentNb()
				))
			) 
		{
			if (lg > sumOfPassedLengths) {
				sizesArray = new long[lengthOfSizes+1];
				sizesArray[lengthOfSizes] = lg - sumOfPassedLengths;
			} else {
				sizesArray = new long[lengthOfSizes];
				sizesArray[lengthOfSizes-1] = (sumOfPassedLengths == lg) ? sizes[lengthOfSizes-1] : sumOfPassedLengths - lg;
			}
			for (int i = 0; i < sizes.length-1; ++i)
				sizesArray[i] = sizes[i];
		}
	}

	@Override
	public void setSplitsNumber(int splitsNb) {
		// TODO Auto-generated method stub
		
	}

	
	public void dummy() {
		f = getFile();
		in = null;
		out = null;
		
	}
	
	
	@Override
	public void split() throws IOException {
		// TODO Auto-generated method stub
		
		if (canSplit()) {
			// premiere maniere ??
			f = getFile();
			in = null;
			out = null;
			long r = f.length() % n;
			long k = f.length() / n;
			if (r > 0)
				++k;
			try {
				in = new BufferedInputStream(new FileInputStream(f));
				for (int i = 0; i < k; i++) {
					String name = f.getAbsolutePath() + "." + (i+1);
					out = new BufferedOutputStream(new FileOutputStream(name));
					long lg = i == k-1 && r>0 ? r: n;
					for (int j = 0; j < lg; j++) {
						out.write(in.read());
					}
					out.close();
				}
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			finally {
				try {
				if (in != null)
					in.close();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
			
			//deuxieme maniere
		}
	}

}
