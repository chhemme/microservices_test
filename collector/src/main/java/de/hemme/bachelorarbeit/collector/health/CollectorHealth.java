package de.hemme.bachelorarbeit.collector.health;

import static org.joda.time.DateTimeConstants.FRIDAY;

import org.joda.time.DateTime;

import com.codahale.metrics.health.HealthCheck;

/**
 * CollectorHealth is a simple {@link HealthCheck}. On friday the 13th it returns
 * {@link Result#unhealthy(String)} as result otherwise it's fine.
 *
 * @see <a href= "https://dropwizard.github.io/dropwizard/manual/core.html#health-checks"
 *      >Dropwizard documentation on health checks</>
 *
 */
public class CollectorHealth extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		DateTime now = DateTime.now();
		if (now.getDayOfMonth() == 13 && now.getDayOfWeek() == FRIDAY) {
			return Result.unhealthy("Unhealthy");
		} else {
			return Result.healthy("Alright");
		}
	}

}
