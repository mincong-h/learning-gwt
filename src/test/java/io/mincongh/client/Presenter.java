package io.mincongh.client;

import com.google.gwt.user.client.ui.HasText;

/**
 * @author Mincong Huang
 */
public class Presenter {
  private Meeting meeting;
  private MeetingView meetingView;
  private RoomScheduler roomScheduler;

  public Presenter(Meeting meeting, MeetingView meetingView, RoomScheduler roomScheduler) {
    this.meeting = meeting;
    this.meetingView = meetingView;
    this.roomScheduler = roomScheduler;
  }

  /**
   * Callback when the view's capacity text box changes
   *
   * @param textField the capacity TextBox widget
   */
  public void requiredCapacityChanged(HasText textField) {
    meeting.setCapacity(Integer.parseInt(textField.getText()));
    if (!roomScheduler.canAcceptCapacityFor(meeting)) {
      meetingView.disableSaveButton();
    }
  }

}
