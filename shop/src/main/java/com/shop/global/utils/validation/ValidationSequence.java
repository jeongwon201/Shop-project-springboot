package com.shop.global.utils.validation;

import javax.validation.GroupSequence;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.shop.global.utils.validation.ValidationGroups.EmailGroup;
import com.shop.global.utils.validation.ValidationGroups.NotBlankGroup;
import com.shop.global.utils.validation.ValidationGroups.PatternGroup;
import com.shop.global.utils.validation.ValidationGroups.SizeGroup;

@GroupSequence({Default.class, NotBlankGroup.class, SizeGroup.class, PatternGroup.class, EmailGroup.class})
public interface ValidationSequence {

}
