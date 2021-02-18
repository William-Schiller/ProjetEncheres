package fr.eni.projetEncheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetEncheres.bean.ArticleVendu;
import fr.eni.projetEncheres.bean.Enchere;
import fr.eni.projetEncheres.bean.Utilisateur;
import fr.eni.projetEncheres.dal.ArticleVenduDAO;
import fr.eni.projetEncheres.dal.DALException;
import fr.eni.projetEncheres.dal.DAOFactory;
import fr.eni.projetEncheres.dal.EnchereDAO;
import fr.eni.projetEncheres.dal.UtilisateurDAO;

public class ArticleVenduManager {
	private static ArticleVenduManager articleVenduManager;
	private ArticleVenduDAO articleVenduDAO;
	private EnchereDAO enchereDAO;
	private UtilisateurDAO utilisateurDAO;
	
	private static List<String> listError;
	
	private ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDAO();
		this.enchereDAO = DAOFactory.getEnchereDAO();
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static ArticleVenduManager getInstance() {
		if(articleVenduManager == null) {
			articleVenduManager = new ArticleVenduManager();
		}
		return articleVenduManager;
	}
	
	public List<String> getListError() {
		return listError;
	}
	
	
	//***************METHODE********************
	/**
	 * @author :ws 
	 * Recherche par no_categori 
	 * 
	 */
	public void insertArticleVendu(ArticleVendu av) throws BLLException {
		listError = new ArrayList<>();
		
		try {
			articleVenduDAO.insert(av);
		} catch (DALException e) {
			listError.add("impossible d'enregistrer cette article");
			throw new BLLException("echec method insertArticleVendu");
		}
	}
	
	/**
	 * @author :ws 
	 * Recherche par no_article
	 * 
	 */
	public List<ArticleVendu> selectAllArticle() throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		
		try {
			list = articleVenduDAO.selectAll();
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticle");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouvé");
		}
		
		return validerVente(list);
	}
	
	/**
	 * @author :ws 
	 * Recherche par no_article
	 * 
	 */
	public List<ArticleVendu> selectAllArticleVenteNonDebutee(int idUtilisateur) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		
		try {
			list = articleVenduDAO.selectAllBeforeDate(idUtilisateur);
		} catch (DALException e) {
			throw new BLLException("echec method selectAllArticleVenteNonDebutee");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun article trouvé");
		}
		
		return list;
	}
	
	/**
	 * @author :ws 
	 * Recherche par no_article
	 * 
	 */
	public ArticleVendu selectArticleById(int idArticle) throws BLLException {
		listError = new ArrayList<>();
		ArticleVendu article = null;
		
		try {
			article = articleVenduDAO.selectByID(idArticle);
		} catch (DALException e) {
			throw new BLLException("echec method selectArticleById");
		}
		
		if(article==null) {
			listError.add("Aucun article trouvé");
		}
		
		return article;
	}
	
	/**
	 * @author :ws 
	 * Recherche par no_categori 
	 * 
	 */
	public List<ArticleVendu> selectByCategorie(int idCategorie) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<ArticleVendu>();
		
		try {
			list = articleVenduDAO.selectByNoCategorie(idCategorie);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("echec method selectByCategorie");
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun élément trouvé pour cette recherche");
		}
		
		return validerVente(list);
	}
	
	/**
	 * @author :ws 
	 * Recherche par mots clé : mot1 ou mot2 ... (dans nom_article ou description)
	 */
	public List<ArticleVendu> selectByArticle(String keyWords) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		List<ArticleVendu> listTemp = new ArrayList<>();
		
		List<String> listKeyWord = returnListString(keyWords);
		
		for (String keyWord : listKeyWord) {
			try {
				listTemp = articleVenduDAO.selectByKeyWord(keyWord);
			} catch (DALException e) {
				throw new BLLException("echec method selectByArticle");
			}
			for (ArticleVendu article : listTemp) {
				list.add(article);
			}
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun élément trouvé pour cette recherche");
		}
	
		return validerVente(list);
	}
	
	/**
	 * @author :ws 
	 * Recherche par no_categori 
	 * + par mots clé : mot1 ou mot2 ... (dans nom_article ou description)
	 */
	public List<ArticleVendu> selectByArticleAndCategorie(String keyWords, int idCategorie) throws BLLException {
		listError = new ArrayList<>();
		List<ArticleVendu> list = new ArrayList<>();
		List<ArticleVendu> listTemp = new ArrayList<>();
		
		List<String> listKeyWord = returnListString(keyWords);
		
		for (String keyWord : listKeyWord) {
			try {
				listTemp = articleVenduDAO.selectByKeyWordAndNoCategorie(keyWord, idCategorie);
			} catch (DALException e) {
				e.printStackTrace();
				throw new BLLException("echec method selectByArticleAndCategorie");
			}
			for (ArticleVendu article : listTemp) {
				list.add(article);
			}
		}
		
		if(list.isEmpty()) {
			listError.add("Aucun élément trouvé pour cette recherche");
		}
	
		return validerVente(list);
	}
	
	protected List<String> returnListString(String keyWords) {
		List<String> list = new ArrayList<>();
		String keyWord;
		int y = 0;
		
		for(int i =1; i< keyWords.length(); i++) {
			if(keyWords.charAt(i)==' ' && keyWords.charAt(i-1)!=' ') {
				keyWord = keyWords.substring(y, i).trim();
				y=i+1;
				i++;
				list.add(keyWord);
			}else if (i == keyWords.length()-1) {
				keyWord = keyWords.substring(y, i+1).trim();
				list.add(keyWord);
			}
		}

		return list;
	}
	
	//***************VERIFICATION******************
	
	/**
	 * @author ws
	 */
	protected List<ArticleVendu> validerVente(List<ArticleVendu> listAValider) {
		List<ArticleVendu> listValider = new ArrayList<>();
			
		for (ArticleVendu a : listAValider) {
			if(a.getDate_fin_encheres().isBefore(LocalDateTime.now())) {
				Enchere enchere = null;
				try {
					enchere = enchereDAO.recupEnchereMax(a.getNo_article());
				} catch (DALException e) {
					e.printStackTrace();
				}
				if(enchere != null) {
					a.setPrix_vente(enchere.getMontant_enchere());
					try {
						Utilisateur vendeur = utilisateurDAO.selectByID(a.getNo_utilisateur());
						vendeur.setCredit(vendeur.getCredit() + enchere.getMontant_enchere());
						utilisateurDAO.update(vendeur);
					} catch (DALException e) {
						e.printStackTrace();
					}
				} else {
					a.setPrix_vente(0);
				}
				try {
					articleVenduDAO.update(a);
				} catch (DALException e) {
					e.printStackTrace();
				}
			} else {
				listValider.add(a);
			}
		}
		
		return listValider;
	}
	
}
