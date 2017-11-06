package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adt.FDBKServlet;
import database.Bruker;
import database.Database;
import database.Forelesning;
import database.Vurdering;

/**
 * Servlet implementation class ForelesningForeleser
 */
@WebServlet("/ForelesningForeleser")
public class ForelesningForeleser extends FDBKServlet {
	private float[] fArr;

	@Override
	protected void get() {
		if (verifyForeleser()) {

			Forelesning forelesning;

			if ((forelesning = getForelesning()) != null) {
				
				calculateData();
				
				setAttribute(fArr[0], Attributes.ikkefornoydUnderveis);
				setAttribute(fArr[1], Attributes.noksofornoydUnderveis);
				setAttribute(fArr[2], Attributes.fornoydUnderveis);

				setAttribute(fArr[3], Attributes.ikkefornoydEtter);
				setAttribute(fArr[4], Attributes.noksofornoydEtter);
				setAttribute(fArr[5], Attributes.fornoydEtter);

				setAttribute(fArr[6], Attributes.samletUnderveis);
				setAttribute(fArr[7], Attributes.samletEtter);
				this.showJSP(JSP.forelesning_foreleser2);
			} else {
				redirect(Servlet.ForelesoversiktForeleser);
			}
		} else {
			setAttribute("Ikke tilgang", Attributes.loginmessage);
			redirect(Servlet.Login);
		}
	}

	@Override
	protected void post() {

	}

	private void calculateData() {
		List<Vurdering> vurderinger = getVurderinger(getForelesning());

		if (vurderinger.size() != 0) {
			fArr = new float[8];
			int antallEtter = 0;
			int antallUnderveis = 0;
			for (int i = 0; i < vurderinger.size(); i++) {
				if (!vurderinger.get(i).getEtter()) {
					antallUnderveis++;
					fArr[6] += vurderinger.get(i).getRating();

					if (vurderinger.get(i).getRating() == 0)
						fArr[0]++;
					else if (vurderinger.get(i).getRating() == 1)
						fArr[1]++;
					else
						fArr[2]++;
				} else {
					antallEtter++;
					fArr[7] += vurderinger.get(i).getRating();

					if (vurderinger.get(i).getRating() == 0)
						fArr[3]++;
					else if (vurderinger.get(i).getRating() == 1)
						fArr[4]++;
					else
						fArr[5]++;
				}
			}

			for (int i = 0; i < fArr.length - 2; i++)
				fArr[i] = Math.round(fArr[i] * 1000f / vurderinger.size()) / 10f;

			if (antallUnderveis != 0)
				fArr[6] = Math.round(fArr[6] * 1000f / (antallUnderveis * 2)) / 10f;
			if (antallEtter != 0)
				fArr[7] = Math.round(fArr[6] * 1000f / (antallEtter * 2)) / 10f;
		}
	}

}
