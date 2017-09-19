import java.util.*;
import java.lang.*;
import java.io.*;

class dependency
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner input = new Scanner(System.in);
        int no_of_topics = input.nextInt();
        final ArrayList<String> list_of_topics = new ArrayList<String>();
        
        //Take input of topics
        for(int i=0;i<no_of_topics;i++) {
            list_of_topics.add(input.next());
        }
        
        boolean [][] map = new boolean [no_of_topics][no_of_topics];
        int no_of_dependencies = input.nextInt();
        
        //Take input about dependency and create adjacency matrix
        for(int i=0;i<no_of_dependencies;i++) {
            int startVertexIndex = list_of_topics.indexOf(input.next()), endVertexIndex = list_of_topics.indexOf(input.next());
            map[startVertexIndex][endVertexIndex] = true;
        }
        
        ArrayList<String> dfs_result = new ArrayList<String>();
        boolean [] traversed = new boolean [no_of_topics];
        
        //Topological sort on grpah
        for(int i=0;i<no_of_topics;i++) {
            if(traversed[i])
                continue;
            ArrayList<String> new_dfs_result = dfs(map, traversed, i, list_of_topics);
            new_dfs_result.addAll(dfs_result);
            dfs_result = new_dfs_result;
        }
        
        //Reset the Traversed array
        Arrays.fill(traversed, false);
        
        //Creating a new graph with reversed edges and rows and columns in order of topological sort
        boolean [][] new_map = new boolean [no_of_topics][no_of_topics];
        for(int i=0;i<no_of_topics;i++) {
            for(int j=0;j<no_of_topics;j++) {
                if(map[i][j]) {
                    new_map[dfs_result.indexOf(list_of_topics.get(j))][dfs_result.indexOf(list_of_topics.get(i))]=true;
                }
            }
        }
        
        ArrayList<ArrayList<String>> ans = new ArrayList<ArrayList<String>>();
        
        //Getting the components of graph
        for(int i=0;i<no_of_topics;i++) {
            if(traversed[i])
                continue;
            ArrayList<String> new_dfs_result = dfs(new_map, traversed, i, dfs_result);
            if(new_dfs_result.size() > 1)
                ans.add(new_dfs_result);
        }
        
        //Sorting the noeds within the components
        for(int i=0;i<ans.size();i++) {
            Collections.sort(ans.get(i), new Comparator<String>() {
                public int compare(String left, String right) {
                    return Integer.valueOf(list_of_topics.indexOf(left)).compareTo(Integer.valueOf(list_of_topics.indexOf(right)));
                }
            });
        }
        
        //Sorting the components
        Collections.sort(ans, new Comparator<ArrayList<String>>() {
            public int compare(ArrayList<String> left, ArrayList<String> right) {
                return Integer.valueOf(list_of_topics.indexOf(left.get(0))).compareTo(Integer.valueOf(list_of_topics.indexOf(right.get(0))));
            }
        });
        
        //Print circular dependencies
        for(int i=0;i<ans.size();i++) {
            for(int j=0;j<ans.get(i).size();j++) {
                if(j==0) {
                    System.out.print(ans.get(i).get(j));
                }
                else {
                    System.out.print(" " + ans.get(i).get(j));
                }
            }
            System.out.println();
        }
    }
    
    //Depth First Search
    public static ArrayList<String> dfs(boolean [][] map, boolean [] traversed, int present_topic_index, ArrayList<String> list_of_topics) {
        
        //Topic Traversed
        traversed[present_topic_index]=true;
        ArrayList<String> dfs_result = new ArrayList<String>();
        
        //Recursive dfs
        for(int i=0;i<map[0].length;i++) {
            if(map[present_topic_index][i]) {
                if(traversed[i]==false) {
                    ArrayList<String> returend_result = dfs(map,traversed,i,list_of_topics);
                    returend_result.addAll(dfs_result);
                    dfs_result = returend_result;
                }
            }
        }
        
        //Topic can now be taught
        dfs_result.add(0,list_of_topics.get(present_topic_index));
        return dfs_result;
    }
}