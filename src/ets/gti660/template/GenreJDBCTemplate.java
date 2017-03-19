package ets.gti660.template;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.GenreDAO;
import ets.gti660.mapper.GenreMapper;
import ets.gti660.model.Genre;

public class GenreJDBCTemplate implements GenreDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public Genre getGenre(Integer id) {
		String SQL = "select * from Genre where id = ?";
		Genre genre = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new GenreMapper());
		return genre;
	}

	@Override
	public List<Genre> listGenres() {
		String SQL = "select * from Genre";
		List<Genre> genres = jdbcTemplateObject.query(SQL, new GenreMapper());
		return genres;
	}

}
