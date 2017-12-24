package com.andersen.spring.facade;

import java.util.List;

import com.andersen.spring.entity.Product;
import com.andersen.spring.entity.User;

/**
 * This class groups all operations related to the ordering goods in the market
 *
 */
public interface MarketFacade {

	/**
	 * Creates new user. The id of user should be auto-generated during creation
	 * and set in the returned object.
	 * 
	 * @param user
	 *            User data.
	 * @return created User{@link User} object with auto-generated id.
	 */
	User createUser(User user);

	/**
	 * Updates user with the given data.
	 * 
	 * @param user
	 *            User data for update.
	 * @return updated User{@link User} object
	 */
	User updateUser(User user);

	/**
	 * Gets user by the given id.
	 * 
	 * @return User.
	 */
	User getUserById(long id);

	/**
	 * Gets user by the given email.
	 * 
	 * @return User which corresponds given email.
	 */
	User getUserByEmail(String email);

	/**
	 * Gets the list of users which names start with the given name. In case
	 * nothing was found method returns empty list.
	 * 
	 * @param name
	 *            full user name or it's part
	 * @return
	 */
	List<User> getUsersByName(String name);

	/**
	 * Deletes user by the given id.
	 * 
	 * @param id
	 *            User id.
	 * @return Flag that shows whether user has been deleted.
	 */
	boolean deleteUser(long id);

	/**
	 * Creates new product. The id of product should be auto-generated during
	 * creation and set in the returned object.
	 * 
	 * @param product
	 *            Product data.
	 * @return created Product{@link Product} object with auto-generated id.
	 */
	Product createProduct(Product product);

	/**
	 * Updates product with the given data.
	 * 
	 * @param product
	 *            product data for update.
	 * @return updated Product{@link Product} object
	 */
	Product updateProduct(Product product);

	/**
	 * Gets product by the given id.
	 * 
	 * @return Product.
	 */
	Product getProductById(long id);

	/**
	 * Gets the list of products by the the given title. In case nothing was
	 * found method returns empty list
	 * 
	 * @param title
	 *            product title or it's part
	 */
	List<Product> getProductsByTitle(String title);

	/**
	 * Gets the list of products for particular user.
	 * 
	 * @param id
	 *            user id
	 */
	List<Product> getProductsByUserId(long id);

	/**
	 * Deletes product by the given id.
	 * 
	 * @param id
	 *            Product id.
	 * @return Flag that shows whether product has been deleted.
	 */
	boolean deleteProductById(long id);
}
