package Review;

import java.io.* ;
import java.util.* ;
import java.awt.* ;
import javax.swing.* ;

import org.apache.commons.csv.* ;
import org.apache.commons.configuration.* ;

import org.jfree.chart.* ;
import org.jfree.chart.plot.* ;
import org.jfree.chart.renderer.xy.XYDotRenderer ;
import org.jfree.data.* ;
import org.jfree.data.statistics.* ;
import org.jfree.data.xy.XYDataset ;
import org.jfree.ui.ApplicationFrame ;

public class Reviewing
{
  TreeMap<Integer, HashSet<Integer>>
	Baskets = new TreeMap<Integer, HashSet<Integer>>() ;

	TreeMap<Integer, Integer>
	numRatingsOfMovies = new TreeMap<Integer, Integer>() ;

	TreeMap<Integer, Double>
	accRatingsOfMovies = new TreeMap<Integer, Double>() ;

	PropertiesConfiguration config ;
	double like_threshold ;
	int outlier_threshold ;

  PropertiesConfiguration config ;
	double like_threshold ;
	int outlier_threshold ;

	public
	MovieRatingData (PropertiesConfiguration config) {
		this.config = config ;
		this.like_threshold = config.getDouble("data.like_threshold") ;
		this.outlier_threshold = config.getInt("data.outlier_threshold") ;
	}

  public void load (FileReader f) throws IOException {
		for (CSVRecord r : CSVFormat.newFormat(',').parse(f)) {
			Integer id   = Integer.parseInt(r.get(0)) ;
			Integer product  = Integer.parseInt(r.get(1)) ;
			Double  user = Double.parseDouble(r.get(2)) ;

			if (numRatingsOfMovies.containsKey(movie) == false) {
				numRatingsOfMovies.put(movie, 1) ;
				accRatingsOfMovies.put(movie, rating) ;
			}
			else {
				numRatingsOfMovies.put(movie, numRatingsOfMovies.get(movie) + 1) ;
				accRatingsOfMovies.put(movie, accRatingsOfMovies.get(movie) + rating) ;
			}

			if (rating >= like_threshold) {
				HashSet<Integer> basket = Baskets.get(user) ;
				if (basket == null) {
					basket = new HashSet<Integer>() ;
					Baskets.put(user, basket) ;
				}
				basket.add(movie) ;
			}
		}

    TreeMap<Integer, HashSet<Integer>>
  	getBaskets() {
  		return Baskets ;
  	}
	}

}
