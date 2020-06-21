package com.example.bustracker.city.street;

import java.util.List;

/*

 * The interface requires only one read-only method, which simplifies support for new
 * data-access strategies.
 */
public interface StreetDetailsService {
    List<Street> getAllStreets();
}
