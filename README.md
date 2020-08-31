# radioactive-parameter-plugin

## Introduction

Dropdown choice parameter that allow user to show/hide other parameters for each option (dynamic parameters).

## Getting started
```
radioactiveParameter {

	name(String value)
	
	description(String value)
	
	// Set default selected options.
	selectedOption(String value)
	
	// List all options and enabled parameters for each option (comma separated).
	options {
		option {
			// Options name.
			optionName(String value)
			// Comma separated list of enabled parameters name.
			enabledParameters(String value)
		}
	}

}```

## Contributing

TODO review the default [CONTRIBUTING](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md) file and make sure it is appropriate for your plugin, if not then add your own one adapted from the base file

Refer to our [contribution guidelines](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md)

