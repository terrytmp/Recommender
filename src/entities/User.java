package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User {
	
	private Integer id;
	private Set<User> neighbourhood;
	private List<Artist> artists;
	
	public User(Integer id) {
		this.id = id;
		neighbourhood = new HashSet<User>();
		artists = new ArrayList<Artist>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<User> getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(Set<User> neighbourhood) {
		this.neighbourhood = neighbourhood;
	}
	
	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	private User(Builder builder){
        this.id = builder.id;
        this.neighbourhood  = builder.neighbourhood;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static final class Builder {
		private Integer id;
		private Set<User> neighbourhood;
		private final String friendsFilePath = "data/user_friends.dat";
		private final String artistsFilePath = "data/user_artists.dat";
		
		public Builder() {
			this.neighbourhood = new HashSet<User>();
		}
		
        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public User build() throws IOException {
    		buildFriendSet();
    		buildNeighbourhood();
    		buildArtists();
    		SortArtists();
            return new User(this);
        }
        
        private void buildFriendSet() throws IOException {
    		try (BufferedReader in = new BufferedReader(new FileReader(friendsFilePath)) ) {
    			in.readLine();
    			
    			String line;
    			while((line = in.readLine()) != null) {
    				String[] lineColumns = line.split("\t");
    				if (Integer.parseInt(lineColumns[0]) == this.id) {
    					this.neighbourhood.add(new User(Integer.parseInt(lineColumns[1])));
    	            }
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	private void buildNeighbourhood() throws IOException {
    		try (BufferedReader in = new BufferedReader(new FileReader(friendsFilePath)) ) {
    			in.readLine();
    			
    			Set<User> neighbourhood = new HashSet<User>();
    			
    			String line;
    			while((line = in.readLine()) != null) {
    				String[] lineColumns = line.split("\t");
    				
    				if (this.neighbourhood.contains(new User(Integer.parseInt(lineColumns[0])))) {
    					neighbourhood.add(new User(Integer.parseInt(lineColumns[1])));
    	            }
    			}
    			
    			this.neighbourhood.addAll(neighbourhood);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    
    	private void buildArtists() {
    		try (BufferedReader in = new BufferedReader(new FileReader(artistsFilePath)) ) {
    			in.readLine();
    			
    			String line;
    			while((line = in.readLine()) != null) {
    				String[] lineColumns = line.split("\t");
    				
    				Integer userId = Integer.parseInt(lineColumns[0]);
    				Integer artistId = Integer.parseInt(lineColumns[1]);
    				Integer weight = Integer.parseInt(lineColumns[2]);
    				
    				if (neighbourhood.contains(new User(userId))) {
    					Iterator<User> it = neighbourhood.iterator();
    					
    					while (it.hasNext()) {
    						User currUser = it.next();
    						if (currUser.equals(new User(userId))) {
    							
    							Artist artist = new Artist();
    							artist.setId(artistId);
    							artist.setWeight(weight);
    							currUser.getArtists().add(artist);
    						}
    					}
    				}
    			}
    			
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	private void SortArtists() {
    		Iterator<User> neighbourhoodIterator = this.neighbourhood.iterator();
    		
    		while (neighbourhoodIterator.hasNext()) {
    			User currentUser = neighbourhoodIterator.next();
    			Collections.sort(currentUser.getArtists());
    			Collections.reverse(currentUser.getArtists());
    			List<Artist> subList = currentUser.getArtists().subList(0, 10); 
    			currentUser.setArtists(subList);
    		}
    	}
	}
}
