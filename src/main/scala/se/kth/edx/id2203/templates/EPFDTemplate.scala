//HOMEWORK 1 - Implement EPFD, defined bellow

package se.kth.edx.id2203.templates

import se.kth.edx.id2203.core.Ports._
import se.kth.edx.id2203.templates.EPFD._ //toremove
import se.kth.edx.id2203.validation._
import se.sics.kompics.network._
import se.sics.kompics.sl.{ Init, _ }
import se.sics.kompics.timer.{ ScheduleTimeout, Timeout, Timer }
import se.sics.kompics.{ KompicsEvent, Start, ComponentDefinition => _, Port => _ }

import scala.collection.mutable._

//Define initialization event
object EPFD {

  //Declare custom message types related to internal component implementation
  case class CheckTimeout(timeout: ScheduleTimeout) extends Timeout(timeout);

  case class HeartbeatReply(seq: Int) extends KompicsEvent;
  case class HeartbeatRequest(seq: Int) extends KompicsEvent;
}

//Define EPFD Implementation
class EPFD(epfdInit: Init[EPFD]) extends ComponentDefinition {

  //EPFD subscriptions
  val timer = requires[Timer];
  val pLink = requires[PerfectLink];
  val epfd = provides[EventuallyPerfectFailureDetector];

  // EPDF component state and initialization
  val self = epfdInit match {
    case Init(s: Address) => s
  }
  val topology = cfg.getValue[List[Address]]("epfd.simulation.topology");
  val delta = cfg.getValue[Long]("epfd.simulation.delay");

  var period = cfg.getValue[Long]("epfd.simulation.delay");
  var alive = Set(cfg.getValue[List[Address]]("epfd.simulation.topology"): _*);
  var suspected = Set[Address]();
  var seqnum = 0;

  def startTimer(delay: Long): Unit = {
    val scheduledTimeout = new ScheduleTimeout(period);
    scheduledTimeout.setTimeoutEvent(CheckTimeout(scheduledTimeout));
    trigger(scheduledTimeout -> timer);
  }

  //EPFD event handlers
  ctrl uponEvent {
    case _: Start => {
      /* WRITE YOUR CODE HERE */
    }
  }

  timer uponEvent {
    case CheckTimeout(_) => {
      if (!alive.intersect(suspected).isEmpty) {
        /* WRITE YOUR CODE HERE */
      }

      seqnum = seqnum + 1;

      for (p <- topology) {
        if (!alive.contains(p) && !suspected.contains(p)) {

         /* WRITE YOUR CODE HERE */

        } else if (alive.contains(p) && suspected.contains(p)) {
          suspected = suspected - p;
          trigger(Restore(p) -> epfd);
        }
        trigger(PL_Send(p, HeartbeatRequest(seqnum)) -> pLink);
      }

      alive = Set[Address]();
      startTimer(period);
    }
  }

  pLink uponEvent {
    case PL_Deliver(src, HeartbeatRequest(seq)) => {
         /* WRITE YOUR CODE HERE */

    }
    case PL_Deliver(src, HeartbeatReply(seq)) => {
        alive = alive + src
         /* WRITE YOUR CODE HERE */
      
    }
  }
};

object EPFDTemplate extends App {
  checkEPFD[EPFD]();
}
