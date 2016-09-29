package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a person to the address book.\n\t"
            + "under construction, for now, delete the person first then add the edited person's detail";

    public static final String MESSAGE_SUCCESS = "person edited: %1$s";
    public static final String MESSAGE_PERSON_NOT_FOUND = "This person does not exists in the address book";

    private final Person toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }

    public EditCommand(Person toAdd) {
        this.toAdd = toAdd;
    }

    public ReadOnlyPerson getPerson() {
        return toAdd;
    }

    @Override
    public CommandResult execute() {
        addressBook.editPerson(toAdd);
		return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
