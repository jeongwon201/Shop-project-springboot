package com.shop.valid;

import javax.validation.GroupSequence;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.shop.valid.ValidationGroups.EmailGroup;
import com.shop.valid.ValidationGroups.NotBlankGroup;
import com.shop.valid.ValidationGroups.PatternGroup;
import com.shop.valid.ValidationGroups.SizeGroup;

@GroupSequence({Default.class, NotBlankGroup.class, SizeGroup.class, PatternGroup.class, EmailGroup.class})
public interface ValidationSequence {

}
