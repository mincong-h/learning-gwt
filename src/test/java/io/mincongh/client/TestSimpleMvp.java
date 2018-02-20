package io.mincongh.client;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import junit.framework.TestCase;

/**
 * Tests simple MVP (Model + Passive View + Presenter/Controller).
 *
 * @author Mincong Huang
 */
public class TestSimpleMvp extends TestCase {

  public void testAnUnavailableRoomDisablesTheSaveButton() {
    final MeetingView view = createMock(MeetingView.class);
    final RoomScheduler scheduler = createMock(RoomScheduler.class);

    final Meeting meeting = new Meeting();
    final Presenter presenter = new Presenter(meeting, view, scheduler);

    // The schedule service will reply with no available capacity

    // Expecting scheduler refuses meeting due to capacity constraint
    expect(scheduler.canAcceptCapacityFor(meeting)).andReturn(false);
    // Expecting save button disabled in the view
    view.disableSaveButton();

    // When setting scheduler and view in replay mode
    // and changing the required capacity as 225 people
    replay(scheduler);
    replay(view);
    presenter.requiredCapacityChanged(new FakeTextContainer("225"));

    // Then the scheduler is called, the view is updated (button disabled)
    // and the meeting capacity has been changed
    verify(scheduler);
    verify(view);
    assertEquals("Should have updated the model's capacity", 225, meeting.getCapacity());
  }
}
