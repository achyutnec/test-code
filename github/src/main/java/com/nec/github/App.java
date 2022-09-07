package com.nec.github;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
	static GitHub github = null;
    static {
    	try {
			github = new GitHubBuilder().withPassword("achyutnec", "necindia@123").build();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static void main( String[] args ) throws IOException
    {
		String repoName = "test-code";
    	System.out.println("is Merge request Accepted == "+isMergeRequestAccepted(repoName, 1));
    	System.out.println("getMetaData == "+getMetaData(repoName));
    }
    
    public static boolean isMergeRequestAccepted(String repoName, Integer mergeRequestid) throws IOException {
    	return github.getRepository(repoName).getPullRequest(mergeRequestid).isMerged();
    }
    
    public static Map<String, Object> getMetaData(String repoName) throws IOException {
    	GHRepository repo = github.getRepository(repoName);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("starsCount", repo.getStargazersCount());
    	map.put("forksCount", repo.getForksCount());
    	map.put("age", repo.getCreatedAt());
    	return map;
    }
}
